package com.vladuken.features.events.presentation.list

import com.vladuken.features.events.domain.models.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

class EventListViewModel : BaseEventListViewModel() {

    override val state = MutableStateFlow(EventsOutput.Success(emptyList()))

    init {
        //TODO Remove mocks
        val list = listOf(
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
            Event(Random.nextInt().toString()),
        )

        state.value = EventsOutput.Success(list)
    }
}