package com.vladuken.features.events.domain.usecases

import com.vladuken.features.events.domain.models.Event

interface FetchFavoriteEventsUseCase {
    suspend operator fun invoke(): List<Event>
}