package com.bn.taipeitravelinfo.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bn.taipeitravelinfo.arch.BaseFragment
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.databinding.FragmentAttractionDetailBinding
import com.bn.taipeitravelinfo.ui.adapter.ImagePagerAdapter
import com.bn.taipeitravelinfo.util.CenterLinearLayoutManager

class AttractionDetailFragment : BaseFragment<FragmentAttractionDetailBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            imagePagerList.apply {
                adapter = ImagePagerAdapter(object : OnItemClickListener<String> {
                    override fun onItemClick(item: String) {

                    }
                })
                layoutManager = CenterLinearLayoutManager(
                    requireActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }
        }
    }
}