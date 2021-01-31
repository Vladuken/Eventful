package com.vladuken.features.events.domain.usecases.cache

import com.vladuken.features.events.domain.models.Event


interface ToggleFavoriteEventUseCase {
    suspend operator fun invoke(event: Event)
}