package com.bn.taipeitravelinfo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.bn.taipeitravelinfo.data.datasource.AttractionInfoPagingDataSource
import com.bn.taipeitravelinfo.data.datasource.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaipeiTravelInfoRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    fun getAttractions() = Pager(config = PagingConfig(pageSize = 30, prefetchDistance = 2),
        pagingSourceFactory = { AttractionInfoPagingDataSource(remoteDataSource) }).liveData
}