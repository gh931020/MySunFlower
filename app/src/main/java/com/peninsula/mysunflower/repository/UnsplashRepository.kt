package com.peninsula.mysunflower.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import com.peninsula.mysunflower.api.UnsplashService
import com.peninsula.mysunflower.data.UnsplashPagingSource
import com.peninsula.mysunflower.data.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnsplashRepository @Inject constructor(private val service: UnsplashService) {

    fun getSearchResultStream(query: String): Flow<PagingData<UnsplashPhoto>> {
        return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
                pagingSourceFactory = { UnsplashPagingSource(service, query) }
        ).flow
    }

    companion object{
        private const val NETWORK_PAGE_SIZE = 25
    }

}
