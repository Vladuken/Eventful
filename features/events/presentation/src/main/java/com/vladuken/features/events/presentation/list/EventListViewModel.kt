package com.vladuken.features.events.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.vladuken.features.events.domain.usecases.CachePagedEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchPagedEventsUseCase
import com.vladuken.features.events.presentation.list.adapter.EventsPagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventListViewModel(
    fetchNetworkEventsUseCase: FetchPagedEventsUseCase,
    fetchCachedEventsUseCase: FetchPagedEventsUseCase,
    cachePagedEventsUseCase: CachePagedEventsUseCase
) : BaseEventListViewModel() {

    override val state: MutableStateFlow<EventsOutput> =
        MutableStateFlow(EventsOutput.Loading)

    private val defaultPagingConfig = PagingConfig(
        pageSize = 10,
        initialLoadSize = 10
    )

    init {
        viewModelScope.launch {
            Pager(config = defaultPagingConfig) {
                EventsPagingSource(
                    fetchNetworkEventsUseCase,
                    fetchCachedEventsUseCase,
                    cachePagedEventsUseCase
                )
            }
                .flow
                .collect { state.value = EventsOutput.Success(it) }
        }
    }
}

