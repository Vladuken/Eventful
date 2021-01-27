package com.vladuken.features.events.presentation.list.adapter

import androidx.paging.PagingSource
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.models.usecases.FetchEventsUseCase

class EventsPagingSource(
    private val fetchEventsUseCase: FetchEventsUseCase
) : PagingSource<Int, Event>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Event> {
        return LoadResult.Page(
            data = fetchEventsUseCase.fetch(),
            prevKey = null,
            nextKey = null
        )
    }
}