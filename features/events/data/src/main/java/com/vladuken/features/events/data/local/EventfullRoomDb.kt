package com.vladuken.features.events.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.model.RoomEvent

@Database(entities = [RoomEvent::class], version = 1)
abstract class EventfullRoomDb : RoomDatabase() {

    abstract fun eventDao(): RoomEventDao

}