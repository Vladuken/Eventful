package com.vladuken.features.events.data.network.usecases

import com.vladuken.features.events.data.network.api.EventAPI
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase


class NetworkFetchEventsUseCase(
    private val eventApi: EventAPI
) : FetchEventsUseCase {

    override suspend fun invoke(): List<Event> {
        return eventApi.getEvents(
            appKey = "CKKnt488bNT6HK2c",
            keyWords = "books",
            locationString = "San Diego",
            dateString = "Future",
            pageSize = 100,
            pageNumber = 1
        ).toDomain()
    }

}