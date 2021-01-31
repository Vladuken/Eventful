package com.vladuken.features.events.presentation.list

import androidx.lifecycle.ViewModel
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.presentation.model.itemcallbacks.entity.FavoriteEvent
import kotlinx.coroutines.flow.StateFlow

abstract class BaseEventListViewModel : ViewModel() {

    abstract val state: StateFlow<EventsOutput>

    abstract fun refresh()

    abstract fun toggleEvent(event: Event)

    sealed class EventsOutput {
        data class Success(val events: List<FavoriteEvent>) : EventsOutput()
        data class Failure(val error: Throwable) : EventsOutput()
        object Loading : EventsOutput()
    }

}