package com.bn.taipeitravelinfo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bn.taipeitravelinfo.arch.ObserveStateFragment
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.databinding.FragmentAttractionInfoListBinding
import com.bn.taipeitravelinfo.ui.adapter.AttractionInfoListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AttractionInfoListFragment : ObserveStateFragment<FragmentAttractionInfoListBinding>() {
    override val viewModel: AttractionInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            attractionList.adapter = setupAttractionInfoListAdapter()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            observeAttractions()
        }
    }

    private fun setupAttractionInfoListAdapter() =
        AttractionInfoListAdapter(object : OnItemClickListener<Attraction> {
            override fun onItemClick(item: Attraction) {

            }
        })

    private suspend fun observeAttractions() =
        viewModel.getAttractions().observe(viewLifecycleOwner) { res ->
            handleState(res) {
                res.data?.let {
                    (binding.attractionList.adapter as AttractionInfoListAdapter).addItems(it)
                }
            }
        }
}