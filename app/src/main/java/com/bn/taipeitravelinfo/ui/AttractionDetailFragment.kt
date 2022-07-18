package com.bn.taipeitravelinfo.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bn.taipeitravelinfo.arch.BaseFragment
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.databinding.FragmentAttractionDetailBinding
import com.bn.taipeitravelinfo.ui.adapter.ImagePagerAdapter
import com.bn.taipeitravelinfo.util.CenterLinearLayoutManager

class AttractionDetailFragment : BaseFragment<FragmentAttractionDetailBinding>() {
    private val args: AttractionDetailFragmentArgs by navArgs()
    private lateinit var selectedAttraction: Attraction

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedAttraction = args.attraction
        with(binding) {
            imagePagerList.apply {
                adapter = ImagePagerAdapter(object : OnItemClickListener<Attraction.ImageSource> {
                    override fun onItemClick(item: Attraction.ImageSource) {
                        //todo: view original image
                    }
                }).apply {
                    addItems(selectedAttraction.images)
                }
                layoutManager = CenterLinearLayoutManager(
                    requireActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }
        }
    }
}