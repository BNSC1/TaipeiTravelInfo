package com.bn.taipeitravelinfo.data.datasource.remote

import com.bn.taipeitravelinfo.data.model.result.AttractionsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface RemoteDataSource {
    @GET("{lang}/Attractions/All")
    suspend fun getAttractions(@Path("lang") language: String = "zh-tw"): Response<AttractionsResult>
}