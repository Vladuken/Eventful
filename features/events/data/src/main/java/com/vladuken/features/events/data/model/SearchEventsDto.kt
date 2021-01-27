package com.vladuken.features.events.data.model

import com.vladuken.features.events.domain.models.PagedEventList

data class SearchEventsDto(
    val last_item: Any?,
    val total_items: Int,
    val first_item: Any?,
    val page_number: Int,
    val page_size: Int,
    val page_items: Any?,
    val search_time: Double,
    val page_count: Int,

    val events: EventListDTO
) {
    fun toPagedDomain(): PagedEventList {
        return PagedEventList(
            events = events.event.map(EventDTO::toDomain),
            currentPage = page_number,
            pageCount = page_count
        )
    }
}