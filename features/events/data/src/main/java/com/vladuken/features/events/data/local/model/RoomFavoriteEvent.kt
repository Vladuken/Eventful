package com.vladuken.features.events.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladuken.features.events.domain.models.Event
import java.util.*

@Entity
data class RoomFavoriteEvent(
    @PrimaryKey
    val id: String,
    val title: String,
    val date: Long,
) {

    fun toDomain(): Event {
        return Event(
            id = id,
            title = title,
            date = Date(date),
        )
    }

    companion object {
        fun from(event: Event): RoomFavoriteEvent {
            return RoomFavoriteEvent(
                id = event.id,
                title = event.title,
                date = event.date.time,
            )
        }
    }

}