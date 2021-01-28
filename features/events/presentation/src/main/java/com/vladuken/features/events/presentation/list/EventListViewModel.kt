package com.vladuken.features.events.presentation.list

import androidx.lifecycle.viewModelScope
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventListViewModel(
    private val fetchEventsUseCase: FetchEventsUseCase
) : BaseEventListViewModel() {

    override val state: MutableStateFlow<EventsOutput> =
        MutableStateFlow(EventsOutput.Loading)

    init {
        fetchEvents()
    }

    override fun refresh() {
        fetchEvents()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            try {
                state.value = EventsOutput.Loading
                state.value = EventsOutput.Success(fetchEventsUseCase())
            } catch (t: Throwable) {
                state.value = EventsOutput.Failure(t)
            }
        }
    }
}

