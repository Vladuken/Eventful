package com.vladuken.features.events.data.usecases

import com.vladuken.features.events.data.api.EventAPI
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.models.usecases.FetchEventsUseCase


class NetworkFetchEventsUseCase(
    private val eventApi: EventAPI
) : FetchEventsUseCase {
    override suspend fun fetch(): List<Event> {
        //TODO Improvement
        return eventApi.getEvents(
            "CKKnt488bNT6HK2c",
            "books",
            "San Diego",
            "Future"
        ).toDomain()
    }
}