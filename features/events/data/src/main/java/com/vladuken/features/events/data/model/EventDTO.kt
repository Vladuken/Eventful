package com.vladuken.features.events.data.model

import com.vladuken.features.events.domain.models.Event

data class EventDTO(
    val title: String
) {
    fun toDomain(): Event {
        return Event(title)
    }
}