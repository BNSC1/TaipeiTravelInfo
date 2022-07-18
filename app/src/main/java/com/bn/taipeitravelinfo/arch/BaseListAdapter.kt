package com.bn.taipeitravelinfo.arch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseListAdapter<Binding : ViewBinding, Item : Any> :
    RecyclerView.Adapter<BaseViewHolder<Binding, Item>>() {
    protected abstract val items: MutableList<Item>
    abstract val bindAction: (binding: Binding, item: Item) -> Unit
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

    fun addItems(i: List<Item>) = items.addAll(i)

    fun clearItems() = let { if (items.isNotEmpty()) items.clear() }

    fun replaceItems(i: List<Item>) {
        clearItems()
        addItems(i)
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