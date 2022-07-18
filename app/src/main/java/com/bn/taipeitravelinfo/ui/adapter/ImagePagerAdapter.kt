package com.bn.taipeitravelinfo.ui.adapter

import com.bn.taipeitravelinfo.arch.BaseListAdapter
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.databinding.ItemPagerImageBinding
import com.bumptech.glide.Glide

class ImagePagerAdapter(private val onItemClickListener: OnItemClickListener<String>) :
    BaseListAdapter<ItemPagerImageBinding, String>() {
    override val items = mutableListOf<String>()
    override val bindAction = { binding: ItemPagerImageBinding, item: String ->
        with(binding) {
            Glide.with(root).load(item).into(image)
            root.setOnClickListener { onItemClickListener.onItemClick(item) }
        }
    }
}