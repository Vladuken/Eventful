package com.vladuken.features.events.domain.usecases.cache

interface ClearCacheEventsUseCase {
    suspend operator fun invoke()
}