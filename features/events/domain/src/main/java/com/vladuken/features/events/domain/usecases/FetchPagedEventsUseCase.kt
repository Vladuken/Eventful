package com.vladuken.features.events.domain.usecases

import com.vladuken.features.events.domain.models.PagedEventList

interface FetchPagedEventsUseCase {
    suspend fun fetch(pageSize: Int, pageNumber: Int): PagedEventList
}