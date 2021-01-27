package com.vladuken.features.events.data.local.usecases

import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.model.RoomEvent
import com.vladuken.features.events.domain.models.PagedEventList
import com.vladuken.features.events.domain.usecases.FetchPagedEventsUseCase

class RoomFetchPagedEventsUseCase(
    private val roomEventDao: RoomEventDao
) : FetchPagedEventsUseCase {
    override suspend fun fetch(pageSize: Int, pageNumber: Int): PagedEventList {
        return RoomEvent.toDomain(roomEventDao.fetchPage(pageNumber))
    }
}