package com.bn.taipeitravelinfo.data.repository

import com.bn.taipeitravelinfo.data.datasource.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaipeiTravelInfoRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getAttractions() = remoteDataSource.getAttractions()
}