package com.bn.taipeitravelinfo.arch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class ClickableListAdapter<Binding : ViewBinding, Item : Any>(
    private val clickListener: OnItemClickListener<Item>,
    private val bindAction: (binding: Binding, item: Item) -> Unit,
) :
    RecyclerView.Adapter<BaseViewHolder<Binding, Item>>() {

    private val items = mutableListOf<Item>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<Binding, Item> {
        return BaseViewHolder(
            initViewBinding(parent), bindAction
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Binding, Item>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

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

    fun addItems(items: List<Item>) {
        this.items.let {
            val oldSize = it.size
            it.addAll(oldSize, items)
            notifyItemRangeInserted(oldSize, items.size)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceItems(items: List<Item>) {
        this.items.let {
            it.clear()
            it.addAll(items)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItems() {
        this.items.let {
            it.clear()
            notifyDataSetChanged()
        }
    }
}