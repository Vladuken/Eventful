package com.vladuken.features.events.data.local.usecases.recent

import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.domain.usecases.cache.ClearCacheRecentEventsUseCase


class RoomClearCacheRecentEventsUseCase(
    private val eventDao: RoomEventDao
) : ClearCacheRecentEventsUseCase {

    override suspend fun invoke() {
        eventDao.clearAll()
    }

}