package com.vladuken.features.events.data.local.usecases

import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.domain.usecases.cache.ClearCacheEventsUseCase


class RoomClearCacheEventsUseCase(
    private val eventDao: RoomEventDao
) : ClearCacheEventsUseCase {

    override suspend fun invoke() {
        eventDao.clearAll()
    }

}