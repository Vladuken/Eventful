package com.vladuken.features.events.domain.usecases

import com.vladuken.features.events.domain.models.PagedEventList

interface CachePagedEventsUseCase {
    suspend fun cache(pagedEventList: PagedEventList)
}