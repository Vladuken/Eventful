package com.vladuken.features.events.presentation.list

import kotlinx.coroutines.flow.MutableStateFlow

class EventListViewModel : BaseEventListViewModel() {

    override val state = MutableStateFlow(EventsOutput.Success(emptyList()))

}