package com.vladuken.features.events.presentation.list.adapter

import android.util.Log
import androidx.paging.PagingSource
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchPagedEventsUseCase

class EventsPagingSource(
    private val fetchEventsUseCase: FetchPagedEventsUseCase
) : PagingSource<Int, Event>() {

    private val TAG = "PAGING"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Event> {
        val currentPageToLoad = params.key ?: 1
        val response = fetchEventsUseCase.fetch(params.loadSize, currentPageToLoad)

        //Логгирование
        //TODO add timber
        Log.d(
            TAG,
            "Current page to Load : $currentPageToLoad. Current response page: ${response.currentPage}. PageCount : ${response.pageCount} List size: ${response.events.size} Current list : ${response.events.map { it.title }}"
        )

        return LoadResult.Page(
            data = response.events,
            prevKey = if (response.currentPage == 1) {
                null
            } else {
                response.currentPage - 1
            },
            nextKey = if (response.currentPage < response.pageCount) {
                response.currentPage + 1
            } else {
                null
            }
        )
    }
}