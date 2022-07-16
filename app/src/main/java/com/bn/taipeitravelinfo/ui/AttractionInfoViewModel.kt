package com.bn.taipeitravelinfo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bn.taipeitravelinfo.arch.NetworkViewModel
import com.bn.taipeitravelinfo.data.model.Attraction
import com.bn.taipeitravelinfo.data.repository.TaipeiTravelInfoRepository
import com.bn.taipeitravelinfo.util.NetworkStatusTool
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttractionInfoViewModel @Inject constructor(
    private val repository: TaipeiTravelInfoRepository,
    override val networkStatusTool: NetworkStatusTool
) : NetworkViewModel() {
    private lateinit var _attractionsLiveData: LiveData<PagingData<Attraction>>
    val attractionsLiveData get() = _attractionsLiveData

    init {
        getAttractions()
    }

    private fun getAttractions() = viewModelScope.launch {
        try {
            val result = repository.getAttractions().cachedIn(viewModelScope)
            _attractionsLiveData = result
        } catch(e: Exception) {
            errorMsg.value = e.message.toString()
        }
    }
}