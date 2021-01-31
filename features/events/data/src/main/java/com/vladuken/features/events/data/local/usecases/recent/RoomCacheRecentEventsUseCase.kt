package com.vladuken.features.events.data.local.usecases.recent

import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.model.RoomEvent
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.cache.CacheRecentEventsUseCase


class RoomCacheRecentEventsUseCase(
    private val eventDao: RoomEventDao
) : CacheRecentEventsUseCase {

    override suspend fun invoke(events: List<Event>) {
        eventDao.insertAll(events.map(RoomEvent.Companion::from))
    }
}