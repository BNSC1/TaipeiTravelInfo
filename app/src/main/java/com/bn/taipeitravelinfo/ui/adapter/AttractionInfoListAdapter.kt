package com.bn.taipeitravelinfo.ui.adapter

import com.bn.taipeitravelinfo.arch.ClickableListAdapter
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.databinding.ItemAttractionInfoListBinding
import com.bumptech.glide.Glide
import timber.log.Timber

class AttractionInfoListAdapter(clickListener: OnItemClickListener<Attraction>) :
    ClickableListAdapter<ItemAttractionInfoListBinding, Attraction>(
        clickListener, { binding, item ->
            with(binding) {
                infoName.text = item.name
                item.images.firstOrNull()?.src?.let {
                    Timber.d("loading ${item.name} with image $it")
                    Glide.with(root)
                        .load(it)
                        .into(infoImage)
                } ?: Timber.d("no image for ${item.name}")
            }
        }
    )