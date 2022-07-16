package com.bn.taipeitravelinfo.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bn.taipeitravelinfo.data.datasource.remote.RemoteDataSource
import com.bn.taipeitravelinfo.data.model.Attraction

class AttractionInfoPagingDataSource(private val apiService: RemoteDataSource) :
    PagingSource<Int, Attraction>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Attraction> {
        val pageNumber = params.key ?: 1
        return try {
            val response = apiService.getAttractions(page = pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.data

            val nextPageQuery = pageNumber + 1

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageQuery
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Attraction>) = 1

}