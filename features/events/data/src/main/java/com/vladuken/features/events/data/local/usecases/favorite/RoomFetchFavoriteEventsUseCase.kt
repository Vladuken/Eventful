package com.vladuken.features.events.data.local.usecases.favorite

import com.vladuken.features.events.data.local.dao.RoomFavoriteEventDao
import com.vladuken.features.events.data.local.model.RoomFavoriteEvent
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchFavoriteEventsUseCase


class RoomFetchFavoriteEventsUseCase(
    private val eventFavoriteEventDao: RoomFavoriteEventDao
) : FetchFavoriteEventsUseCase {

    override suspend fun invoke(): List<Event> {
        return eventFavoriteEventDao
            .readAll()
            .map(RoomFavoriteEvent::toDomain)
    }

}