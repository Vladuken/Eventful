package com.vladuken.features.events.data.network.model

import com.vladuken.features.events.domain.models.Event
import java.util.*

data class EventDTO(
    val id: String,
    val title: String,
    val start_time: Date,
    val venue_url: String
) {
    fun toDomain(): Event {
        return Event(
            id = id,
            title = title,
            date = start_time,
            url = venue_url
        )
    }
}