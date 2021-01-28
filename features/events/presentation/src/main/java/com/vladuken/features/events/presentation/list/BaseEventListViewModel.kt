package com.vladuken.features.events.presentation.list

import androidx.lifecycle.ViewModel
import com.vladuken.features.events.domain.models.Event
import kotlinx.coroutines.flow.StateFlow

abstract class BaseEventListViewModel : ViewModel() {

    abstract val state: StateFlow<EventsOutput>

    abstract fun refresh()

    sealed class EventsOutput {
        data class Success(val events: List<Event>) : EventsOutput()
        data class Failure(val error: Throwable) : EventsOutput()
        object Loading : EventsOutput()
    }

}