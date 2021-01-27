package com.vladuken.features.events.presentation.list

import androidx.lifecycle.ViewModel
import com.vladuken.features.events.domain.models.Event
import kotlinx.coroutines.flow.StateFlow

abstract class BaseEventListViewModel : ViewModel() {

    abstract val state: StateFlow<EventsOutput>

    sealed class EventsOutput {
        data class Success(val events: List<Event>) : EventsOutput()
        object Failure : EventsOutput()
        object Loading : EventsOutput()
    }

}