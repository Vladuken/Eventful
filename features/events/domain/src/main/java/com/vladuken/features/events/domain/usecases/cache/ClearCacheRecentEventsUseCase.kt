package com.vladuken.features.events.domain.usecases.cache

interface ClearCacheRecentEventsUseCase {
    suspend operator fun invoke()
}