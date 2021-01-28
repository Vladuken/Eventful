package com.vladuken.features.events.domain.usecases

import com.vladuken.features.events.domain.models.Event

interface CacheEventsUseCase {
    suspend operator fun invoke(events: List<Event>)
}