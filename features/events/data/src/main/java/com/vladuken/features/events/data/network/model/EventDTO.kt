package com.vladuken.features.events.data.network.model

import com.vladuken.features.events.domain.models.Event
import java.util.*

data class EventDTO(
    val title: String,
    val start_time: Date
) {
    fun toDomain(): Event {
        return Event(title, start_time)
    }
}