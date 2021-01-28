package com.vladuken.features.events.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladuken.features.events.domain.models.Event
import java.util.*

@Entity
data class RoomEvent(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val date: Long,
) {

    fun toDomain(): Event {
        return Event(title, Date(date))
    }

    companion object {
        private fun from(event: Event): RoomEvent {
            return RoomEvent(
                title = event.title,
                date = event.date.time,
            )
        }

    }
}