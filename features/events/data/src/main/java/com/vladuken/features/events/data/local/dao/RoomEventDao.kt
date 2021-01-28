package com.vladuken.features.events.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vladuken.features.events.data.local.model.RoomEvent

@Dao
abstract class RoomEventDao {

    @Query("SELECT * FROM roomevent")
    abstract suspend fun readAll(): List<RoomEvent>

    @Insert
    abstract suspend fun insertAll(events: List<RoomEvent>)

    @Query("DELETE FROM roomevent")
    abstract suspend fun clearAll()

}