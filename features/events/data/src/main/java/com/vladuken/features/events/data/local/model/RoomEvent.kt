package com.vladuken.features.events.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.models.PagedEventList
import java.util.*

@Entity
data class RoomEvent(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val date: Long,
    val currentPage: Int,
    val pageCount: Int
) {

    fun toDomain(): Event {
        return Event(title, Date(date))
    }

    companion object {
        private fun from(event: Event, currentPage: Int, pageCount: Int): RoomEvent {
            return RoomEvent(
                title = event.title,
                date = event.date.time,
                currentPage = currentPage,
                pageCount = pageCount
            )
        }

        fun from(pagedEvent: PagedEventList): List<RoomEvent> {
            return pagedEvent.events.map {
                from(it, pagedEvent.currentPage, pagedEvent.pageCount)
            }
        }

        fun toDomain(roomEvents: List<RoomEvent>): PagedEventList {
            return PagedEventList(
                roomEvents.map { it.toDomain() },
                roomEvents.firstOrNull()?.currentPage ?: 1,
                roomEvents.firstOrNull()?.pageCount ?: 1
            )
        }
    }
}