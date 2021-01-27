package com.vladuken.features.events.data.usecases

import com.vladuken.features.events.data.api.EventAPI
import com.vladuken.features.events.domain.models.PagedEventList
import com.vladuken.features.events.domain.usecases.FetchPagedEventsUseCase


class NetworkFetchPagedEventsUseCase(
    private val eventApi: EventAPI
) : FetchPagedEventsUseCase {
    override suspend fun fetch(pageSize: Int, pageNumber: Int): PagedEventList {
        //TODO Improvement
        return eventApi.getEvents(
            appKey = "CKKnt488bNT6HK2c",
            keyWords = "books",
            locationString = "San Diego",
            dateString = "Future",
            pageSize = pageSize,
            pageNumber = pageNumber
        ).toPagedDomain()
    }
}