package com.vladuken.features.events.data.local.usecases.recent

import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.cache.CacheRecentEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchRecentEventsUseCase


class FetchAndCacheRecentEventsUseCase(
    private val networkFetchRecentEventsUseCase: FetchRecentEventsUseCase,
    private val localFetchRecentEventsUseCase: FetchRecentEventsUseCase,
    private val cacheRecentEventsUseCase: CacheRecentEventsUseCase
) : FetchRecentEventsUseCase {

    override suspend fun invoke(): List<Event> {
        val cachedList = localFetchRecentEventsUseCase()

        return if (cachedList.isEmpty()) {
            val networkEvents = networkFetchRecentEventsUseCase()
            cacheRecentEventsUseCase(networkEvents)
            localFetchRecentEventsUseCase()
        } else {
            cachedList
        }
    }

}