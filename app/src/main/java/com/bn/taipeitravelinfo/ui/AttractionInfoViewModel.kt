package com.bn.taipeitravelinfo.ui

import androidx.lifecycle.liveData
import com.bn.taipeitravelinfo.arch.NetworkViewModel
import com.bn.taipeitravelinfo.data.Resource
import com.bn.taipeitravelinfo.data.repository.TaipeiTravelInfoRepository
import com.bn.taipeitravelinfo.util.NetworkStatusTool
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttractionInfoViewModel @Inject constructor(
    private val repository: TaipeiTravelInfoRepository,
    override val networkStatusTool: NetworkStatusTool
) : NetworkViewModel() {
    suspend fun getAttractions() = liveData {
        tryNetworkAction {
            handleResponse(repository.getAttractions()) { result ->
                emit(Resource.success(result.data))
            }
        }
    }
}