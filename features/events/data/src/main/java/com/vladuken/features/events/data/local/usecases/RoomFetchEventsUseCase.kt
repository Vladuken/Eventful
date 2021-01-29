package com.vladuken.features.events.data.local.usecases

import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.dao.RoomFavoriteEventDao
import com.vladuken.features.events.data.local.model.RoomEvent
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase


class RoomFetchEventsUseCase(
    private val eventDao: RoomEventDao,
    private val favoriteEventDao: RoomFavoriteEventDao
) : FetchEventsUseCase {

    override suspend fun invoke(): List<Event> {
        val cachedEvents = eventDao.readAll()
            .map(RoomEvent::toDomain)

        val favoriteEvents = favoriteEventDao.readAll()
            .map { it.toDomain() }

        return cachedEvents.map { cachedEvent ->
            if (favoriteEvents.find { cachedEvent.id == it.id } == null) {
                cachedEvent
            } else {
                cachedEvent.copy(isFavorite = true)
            }
        }
    }

}