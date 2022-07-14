package com.bn.taipeitravelinfo.arch

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class ClickableViewHolder<Binding : ViewBinding, Item : Any, ClickListener: OnItemClickListener<Item>>(
    private val binding: Binding,
    private val clickListener: ClickListener,
    private val bindAction: (binding: Binding, item: Item, clickListener: ClickListener) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(item: Item) {
        bindAction(binding, item, clickListener)
    }
}