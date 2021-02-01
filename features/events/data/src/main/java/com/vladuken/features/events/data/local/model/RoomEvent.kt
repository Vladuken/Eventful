package com.vladuken.features.events.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladuken.features.events.domain.models.Event
import java.util.*

@Entity
data class RoomEvent(
    @PrimaryKey
    val id: String,
    val title: String,
    val date: Long,
    val url: String
) {

    fun toDomain(): Event {
        return Event(
            id = id,
            title = title,
            date = Date(date),
            url = url
        )
    }

    companion object {
        fun from(event: Event): RoomEvent {
            return RoomEvent(
                id = event.id,
                title = event.title,
                date = event.date.time,
                url = event.url
            )
        }
    }

}