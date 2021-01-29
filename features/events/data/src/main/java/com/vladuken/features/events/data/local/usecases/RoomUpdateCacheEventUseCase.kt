package com.vladuken.features.events.data.local.usecases

import com.vladuken.features.events.data.local.dao.RoomFavoriteEventDao
import com.vladuken.features.events.data.local.model.RoomFavoriteEvent
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.cache.UpdateCacheEventUseCase


class RoomUpdateCacheEventUseCase(
    private val favoriteEventDao: RoomFavoriteEventDao
) : UpdateCacheEventUseCase {

    override suspend fun invoke(event: Event) {
        val favoritesEvents = favoriteEventDao.readAll()
            .map { it.toDomain() }

        if (favoritesEvents.find { it.id == event.id } == null) {
            favoriteEventDao.insert(RoomFavoriteEvent.from(event))
        } else {
            favoriteEventDao.deleteById(event.id)
        }
    }

}