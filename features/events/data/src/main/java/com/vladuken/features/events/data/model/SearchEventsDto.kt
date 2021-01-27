package com.vladuken.features.events.data.model

import com.vladuken.features.events.domain.models.Event

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
    fun toDomain(): List<Event> {
        return events.event.map(EventDTO::toDomain)
    }
}