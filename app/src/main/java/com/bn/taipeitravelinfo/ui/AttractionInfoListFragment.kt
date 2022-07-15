package com.bn.taipeitravelinfo.ui

import android.os.Bundle
import android.view.View
import com.bn.taipeitravelinfo.arch.ObserveStateFragment
import com.bn.taipeitravelinfo.databinding.FragmentAttractionInfoListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AttractionInfoListFragment : ObserveStateFragment<FragmentAttractionInfoListBinding>() {
    @Inject
    override lateinit var viewModel: AttractionInfoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

        }
    }
}