package com.vladuken.features.events.presentation.list

import androidx.lifecycle.viewModelScope
import com.vladuken.features.events.domain.models.usecases.FetchEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventListViewModel(
    private val fetchEventsUseCase: FetchEventsUseCase
) : BaseEventListViewModel() {

    override val state: MutableStateFlow<EventsOutput> =
        MutableStateFlow(EventsOutput.Success(emptyList()))

    init {
        viewModelScope.launch {
            try {
                state.value = EventsOutput.Loading
                val items = fetchEventsUseCase.fetch()
                state.value = EventsOutput.Success(items)
            } catch (e: Exception) {
                state.value = EventsOutput.Failure
            }
        }
    }
}