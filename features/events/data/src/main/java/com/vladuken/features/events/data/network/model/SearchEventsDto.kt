package com.vladuken.features.events.data.network.model

import com.vladuken.features.events.domain.models.Event


data class SearchEventsDto(
    val total_items: Int,
    val events: EventListDTO
) {
    fun toDomain(): List<Event> {
        return events.event.map(EventDTO::toDomain)
    }
}