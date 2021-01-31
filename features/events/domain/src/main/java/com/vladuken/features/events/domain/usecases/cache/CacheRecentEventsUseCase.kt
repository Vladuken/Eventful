package com.vladuken.features.events.domain.usecases.cache

import com.vladuken.features.events.domain.models.Event

interface CacheRecentEventsUseCase {
    suspend operator fun invoke(events: List<Event>)
}