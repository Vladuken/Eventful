package com.vladuken.features.events.data.local.usecases

import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.model.RoomEvent
import com.vladuken.features.events.domain.models.PagedEventList
import com.vladuken.features.events.domain.usecases.CachePagedEventsUseCase

class RoomCachePagedEventsUseCase(
    private val roomEventDao: RoomEventDao
) : CachePagedEventsUseCase {
    override suspend fun cache(pagedEventList: PagedEventList) {
        roomEventDao.insertAll(RoomEvent.from(pagedEventList))
    }
}