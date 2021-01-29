package com.vladuken.features.events.data.local.usecases

import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.model.RoomEvent
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.cache.UpdateCacheEventUseCase


class RoomUpdateCacheEventUseCase(
    private val eventDao: RoomEventDao
) : UpdateCacheEventUseCase {

    override suspend fun invoke(event: Event) {
        eventDao.insert(RoomEvent.from(event))
    }

}