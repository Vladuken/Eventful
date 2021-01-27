package com.vladuken.features.events.presentation.list.adapter

import android.util.Log
import androidx.paging.PagingSource
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.CachePagedEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchPagedEventsUseCase

class EventsPagingSource(
    private val fetchEventsUseCase: FetchPagedEventsUseCase,
    private val fetchLocalEventsUseCase: FetchPagedEventsUseCase,
    private val cachePagedEventsUseCase: CachePagedEventsUseCase
) : PagingSource<Int, Event>() {

    private val TAG = "PAGING"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Event> {
        val currentPageToLoad = params.key ?: 1

        val cachedResponse = fetchLocalEventsUseCase.fetch(params.loadSize, currentPageToLoad)

        val finalResponse = if (cachedResponse.events.isEmpty()) {
            Log.d(TAG, "LOAD FROM NETWORK")
            val response = fetchEventsUseCase.fetch(params.loadSize, currentPageToLoad)
            cachePagedEventsUseCase.cache(response)
            fetchLocalEventsUseCase.fetch(params.loadSize, currentPageToLoad)
        } else {
            Log.d(TAG, "LOAD FROM CACHE")
            cachedResponse
        }

        //TODO add timber
        Log.d(TAG, "Current page to Load : $currentPageToLoad. Current response page: ${finalResponse.currentPage}. PageCount : ${finalResponse.pageCount} List size: ${finalResponse.events.size} Current list : ${finalResponse.events.map { it.title }}")

        return LoadResult.Page(
            data = finalResponse.events,
            prevKey = if (finalResponse.currentPage == 1) {
                null
            } else {
                finalResponse.currentPage - 1
            },
            nextKey = if (finalResponse.currentPage < finalResponse.pageCount) {
                finalResponse.currentPage + 1
            } else {
                null
            }
        )
    }
}