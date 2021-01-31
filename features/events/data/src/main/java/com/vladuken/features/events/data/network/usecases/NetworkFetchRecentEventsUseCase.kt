package com.vladuken.features.events.data.network.usecases

import com.vladuken.features.events.data.network.api.EventAPI
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchRecentEventsUseCase


class NetworkFetchRecentEventsUseCase(
    private val eventApi: EventAPI
) : FetchRecentEventsUseCase {

    private val appKey = "CKKnt488bNT6HK2c"
    private val keyWords = "books"
    private val locationString = "San Diego"
    private val dateString = "Future"

    override suspend fun invoke(): List<Event> {
        val emptyRequest = eventApi.getEvents(
            appKey = appKey,
            keyWords = keyWords,
            locationString = locationString,
            dateString = dateString,
            pageSize = 1,
        )

        return eventApi.getEvents(
            appKey = "CKKnt488bNT6HK2c",
            keyWords = "books",
            locationString = "San Diego",
            dateString = "Future",
            pageSize = emptyRequest.total_items,
        ).toDomain()
    }

}