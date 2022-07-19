package com.bn.taipeitravelinfo.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bn.taipeitravelinfo.arch.CustomPagingDataAdapter
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.databinding.ItemAttractionInfoListBinding
import com.bumptech.glide.Glide
import timber.log.Timber

class AttractionInfoListAdapter(private val clickListener: OnItemClickListener<Attraction>) :
    CustomPagingDataAdapter<ItemAttractionInfoListBinding, Attraction>(
        { binding, item ->
            with(binding) {
                infoName.text = item.name
                infoAddress.text = item.address

                item.images.firstOrNull()?.let {
                    Timber.d("loading ${item.name} with image ${it.src}")
                    Glide.with(root)
                        .load(it.src)
                        .into(infoImage)
                } ?: let {
                    Timber.d("no image for ${item.name}")
                    Glide.with(root).clear(infoImage)
                }
                root.setOnClickListener { clickListener.onItemClick(item) }
            }
        }, object : DiffUtil.ItemCallback<Attraction>() {
            override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction) =
                oldItem == newItem

        })