package com.bn.taipeitravelinfo.ui.adapter

import com.bn.taipeitravelinfo.arch.BaseListAdapter
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.databinding.ItemPagerImageBinding
import com.bumptech.glide.Glide

class ImagePagerAdapter(private val onItemClickListener: OnItemClickListener<Attraction.ImageSource>) :
    BaseListAdapter<ItemPagerImageBinding, Attraction.ImageSource>() {
    override val items = mutableListOf<Attraction.ImageSource>()
    override val bindAction = { binding: ItemPagerImageBinding, item: Attraction.ImageSource ->
        with(binding) {
            Glide.with(root).load(item.src).into(image)
            root.setOnClickListener { onItemClickListener.onItemClick(item) }
        }
    }
}