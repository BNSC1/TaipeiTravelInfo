package com.bn.taipeitravelinfo.ui.adapter

import com.bn.taipeitravelinfo.arch.ClickableListAdapter
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.databinding.ItemAttractionInfoListBinding
import com.bumptech.glide.Glide

class AttractionInfoListAdapter(clickListener: OnItemClickListener<Attraction>) :
    ClickableListAdapter<ItemAttractionInfoListBinding, Attraction>(
        clickListener, { binding, item ->
            with(binding) {
                infoName.text = item.name
                Glide.with(root)
                    .load(item.images.url.firstOrNull())
                    .into(infoImage)
            }
        }
    )