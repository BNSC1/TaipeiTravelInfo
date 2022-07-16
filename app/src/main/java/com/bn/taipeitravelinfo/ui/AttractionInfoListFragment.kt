package com.bn.taipeitravelinfo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bn.taipeitravelinfo.R
import com.bn.taipeitravelinfo.arch.ObserveStateFragment
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.databinding.FragmentAttractionInfoListBinding
import com.bn.taipeitravelinfo.ui.adapter.AttractionInfoListAdapter
import com.bn.taipeitravelinfo.util.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AttractionInfoListFragment : ObserveStateFragment<FragmentAttractionInfoListBinding>() {
    override val viewModel: AttractionInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            attractionList.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
            }
            attractionList.adapter = setupAttractionInfoListAdapter()
            attractionList.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.item_recyclerview_padding).toInt()
                )
            )
        }

        observeAttractions()
    }

    private fun setupAttractionInfoListAdapter() =
        AttractionInfoListAdapter(object : OnItemClickListener<Attraction> {
            override fun onItemClick(item: Attraction) {

            }
        })

    private fun observeAttractions() =
        viewModel.attractionsLiveData.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                (binding.attractionList.adapter as AttractionInfoListAdapter).submitData(it)
            }
        }
}