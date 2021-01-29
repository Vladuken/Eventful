package com.vladuken.features.events.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladuken.features.events.data.local.model.RoomFavoriteEvent

@Dao
abstract class RoomFavoriteEventDao {

    @Query("SELECT * FROM roomfavoriteevent")
    abstract suspend fun readAll(): List<RoomFavoriteEvent>

    @Insert
    abstract suspend fun insertAll(events: List<RoomFavoriteEvent>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(event: RoomFavoriteEvent)

    @Query("DELETE FROM roomfavoriteevent")
    abstract suspend fun clearAll()

    @Query("DELETE FROM roomfavoriteevent WHERE id = :id")
    abstract suspend fun deleteById(id: String)

}