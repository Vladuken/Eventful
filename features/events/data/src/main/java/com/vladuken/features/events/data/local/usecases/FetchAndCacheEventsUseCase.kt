package com.vladuken.features.events.data.local.usecases

import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.CacheEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase


class FetchAndCacheEventsUseCase(
    private val networkFetchEventsUseCase: FetchEventsUseCase,
    private val localFetchEventsUseCase: FetchEventsUseCase,
    private val cacheEventsUseCase: CacheEventsUseCase
) : FetchEventsUseCase {

    override suspend fun invoke(): List<Event> {
        val cachedList = localFetchEventsUseCase()

        return if (cachedList.isEmpty()) {
            val networkEvents = networkFetchEventsUseCase()
            cacheEventsUseCase(networkEvents)
            localFetchEventsUseCase()
        } else {
            cachedList
        }
    }

}