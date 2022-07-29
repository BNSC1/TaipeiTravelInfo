package com.bn.taipeitravelinfo.ui.adapter

import com.bn.taipeitravelinfo.arch.BaseListAdapter
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.databinding.ItemAttractionDetailLinkBinding

data class DetailLink(val title: String, val url: String)

class AttractionDetailLinkListAdapter(private val clickListener: OnItemClickListener<DetailLink>) :
    BaseListAdapter<ItemAttractionDetailLinkBinding, DetailLink>() {
    override val bindAction = { binding: ItemAttractionDetailLinkBinding, item: DetailLink ->
        with(binding.linkBtn) {
            text = item.title
            setOnClickListener { clickListener.onItemClick(item) }
        }
    }
}