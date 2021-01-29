package com.vladuken.features.events.presentation.list

import androidx.lifecycle.viewModelScope
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.ClearCacheEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.UpdateCacheEventUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventListViewModel(
    private val fetchEventsUseCase: FetchEventsUseCase,
    private val clearCacheEventsUseCase: ClearCacheEventsUseCase,
    private val updateEventUseCase: UpdateCacheEventUseCase
) : BaseEventListViewModel() {

    override val state: MutableStateFlow<EventsOutput> =
        MutableStateFlow(EventsOutput.Loading)

    init {
        viewModelScope.launch {
            fetchEvents()
        }
    }

    override fun refresh() {
        viewModelScope.launch {
            clearCacheEventsUseCase()
            fetchEvents()
        }
    }

    override fun toggleEvent(event: Event) {
        viewModelScope.launch {
            updateEventUseCase(event.copy(isFavorite = !event.isFavorite))
            fetchEvents()
        }
    }

    private suspend fun fetchEvents() {
        try {
            state.value = EventsOutput.Loading
            state.value = EventsOutput.Success(fetchEventsUseCase())
        } catch (t: Throwable) {
            state.value = EventsOutput.Failure(t)
        }
    }
}

