package com.vladuken.features.events.data.local.usecases

import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.model.RoomEvent
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase


class RoomFetchEventsUseCase(
    private val eventDao: RoomEventDao
) : FetchEventsUseCase {

    override suspend fun invoke(): List<Event> {
        return eventDao.readAll()
            .map(RoomEvent::toDomain)
    }

}