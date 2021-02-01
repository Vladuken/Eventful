package com.vladuken.features.events.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.dao.RoomFavoriteEventDao
import com.vladuken.features.events.data.local.model.RoomEvent
import com.vladuken.features.events.data.local.model.RoomFavoriteEvent

@Database(
    entities = [RoomEvent::class, RoomFavoriteEvent::class],
    version = 2
)
abstract class EventfullRoomDb : RoomDatabase() {

    abstract fun eventDao(): RoomEventDao

    abstract fun favoriteEventDao(): RoomFavoriteEventDao

}