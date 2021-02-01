package com.vladuken.features.events.presentation.events.favorite

import androidx.lifecycle.viewModelScope
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchFavoriteEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.ToggleFavoriteEventUseCase
import com.vladuken.features.events.presentation.events.BaseRecentEventListViewModel
import com.vladuken.features.events.presentation.model.itemcallbacks.entity.FavoriteEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoriteEventListViewModel(
    private val fetchFavoriteEventsUseCase: FetchFavoriteEventsUseCase,
    private val toggleEventUseCase: ToggleFavoriteEventUseCase
) : BaseRecentEventListViewModel() {


    override val state: MutableStateFlow<EventsOutput> =
        MutableStateFlow(EventsOutput.Loading)

    init {
        viewModelScope.launch {
            fetchEvents()
        }
    }

    override fun refresh() {
        viewModelScope.launch {
            fetchEvents()
        }
    }

    override fun toggleEvent(event: Event) {
        viewModelScope.launch {
            toggleEventUseCase(event)
            fetchEvents()
        }
    }

    private suspend fun fetchEvents() {
        try {
            state.value = EventsOutput.Loading
            val favoriteEvents = fetchFavoriteEventsUseCase()
                .map { FavoriteEvent(it, true) }
            state.value = EventsOutput.Success(favoriteEvents)
        } catch (t: Throwable) {
            state.value = EventsOutput.Failure(t)
        }
    }
}

