package com.bn.taipeitravelinfo.arch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class ClickableListAdapter<Binding : ViewBinding, Item : Any, ClickListener : OnItemClickListener<Item>>(
    private val clickListener: ClickListener,
    private val bindAction: (binding: Binding, item: Item, clickListener: ClickListener) -> Unit,
) :
    RecyclerView.Adapter<ClickableViewHolder<Binding, Item, ClickListener>>() {
    private var items = mutableListOf<Item>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ClickableViewHolder<Binding, Item, ClickListener> {
        return ClickableViewHolder(
            initViewBinding(parent), clickListener, bindAction
        )
    }

    override fun onBindViewHolder(
        holder: ClickableViewHolder<Binding, Item, ClickListener>,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceItems(items: List<Item>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<Item>) {
        this.items.addAll(items.size, items)
        notifyDataSetChanged()
    }

    @Suppress("UNCHECKED_CAST")
    private fun initViewBinding(parent: ViewGroup): Binding {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, LayoutInflater.from(parent.context), parent, false) as Binding
    }
}
