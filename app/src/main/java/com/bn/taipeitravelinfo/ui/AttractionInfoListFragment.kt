package com.bn.taipeitravelinfo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.bn.taipeitravelinfo.R
import com.bn.taipeitravelinfo.arch.ObserveStateFragment
import com.bn.taipeitravelinfo.arch.OnItemClickListener
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.databinding.FragmentAttractionInfoListBinding
import com.bn.taipeitravelinfo.ktx.collectLatestLifecycleFlow
import com.bn.taipeitravelinfo.ui.adapter.AttractionInfoListAdapter
import com.bn.taipeitravelinfo.util.MarginItemDecoration
import com.bn.taipeitravelinfo.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AttractionInfoListFragment : ObserveStateFragment<FragmentAttractionInfoListBinding>() {
    override val viewModel: AttractionInfoViewModel by viewModels()
    private val listAdapter = setupAttractionInfoListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            attractionList.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
                adapter = listAdapter.withLoadStateHeaderAndFooter(
                    PagingLoadStateAdapter(listAdapter),
                    PagingLoadStateAdapter(listAdapter)
                ) //todo: not showing header
                addItemDecoration(
                    MarginItemDecoration(
                        resources.getDimension(R.dimen.item_recyclerview_padding).toInt()
                    )
                )
            }
            swipeRefreshLayout.setOnRefreshListener { listAdapter.refresh() }
            collectLatestLoadState()
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
                listAdapter.submitData(it)
            }
        }

    private fun FragmentAttractionInfoListBinding.collectLatestLoadState() =
        listAdapter.loadStateFlow.collectLatestLifecycleFlow(viewLifecycleOwner) {
            swipeRefreshLayout.isRefreshing = it.refresh is LoadState.Loading
        }
}