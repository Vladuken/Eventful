package com.vladuken.features.events.data.local.usecases.favorite

import com.vladuken.features.events.data.local.dao.RoomFavoriteEventDao
import com.vladuken.features.events.data.local.model.RoomFavoriteEvent
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.cache.ToggleFavoriteEventUseCase


class RoomToggleFavoriteEventUseCase(
    private val eventDao: RoomFavoriteEventDao
) : ToggleFavoriteEventUseCase {

    override suspend fun invoke(event: Event) {
        val item = RoomFavoriteEvent.from(event)

        if (eventDao.exists(event.id)) {
            eventDao.delete(item)
        } else {
            eventDao.insert(item)
        }
    }

}