package com.vladuken.features.events.presentation.events.recent

import androidx.lifecycle.viewModelScope
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchFavoriteEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchRecentEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.ClearCacheRecentEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.ToggleFavoriteEventUseCase
import com.vladuken.features.events.presentation.model.itemcallbacks.entity.FavoriteEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecentEventListViewModel(
    private val fetchRecentEventsUseCase: FetchRecentEventsUseCase,
    private val fetchFavoriteEventsUseCase: FetchFavoriteEventsUseCase,
    private val clearCacheRecentEventsUseCase: ClearCacheRecentEventsUseCase,
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
            clearCacheRecentEventsUseCase()
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
            val recentEvents = fetchRecentEventsUseCase()

            val events = recentEvents.map {
                FavoriteEvent(it, favoriteEvents.contains(it))
            }

            state.value = EventsOutput.Success(events)
        } catch (t: Throwable) {
            state.value = EventsOutput.Failure(t)
        }
    }
}

