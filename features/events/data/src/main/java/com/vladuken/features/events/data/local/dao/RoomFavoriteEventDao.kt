package com.vladuken.features.events.data.local.dao

import androidx.room.*
import com.vladuken.features.events.data.local.model.RoomEvent
import com.vladuken.features.events.data.local.model.RoomFavoriteEvent

@Dao
abstract class RoomFavoriteEventDao {

    @Query("SELECT * FROM roomfavoriteevent")
    abstract suspend fun readAll(): List<RoomFavoriteEvent>

    @Query("SELECT EXISTS (SELECT 1 FROM roomfavoriteevent WHERE id = :id)")
    abstract suspend fun exists(id: String): Boolean

    @Delete
    abstract suspend fun delete(event: RoomFavoriteEvent)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(event: RoomFavoriteEvent)

}