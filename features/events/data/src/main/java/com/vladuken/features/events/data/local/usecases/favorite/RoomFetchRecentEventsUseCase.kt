package com.vladuken.features.events.data.local.usecases.favorite

import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.model.RoomEvent
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchRecentEventsUseCase


class RoomFetchRecentEventsUseCase(
    private val eventDao: RoomEventDao
) : FetchRecentEventsUseCase {

    override suspend fun invoke(): List<Event> {
        return eventDao.readAll()
            .map(RoomEvent::toDomain)
    }

}