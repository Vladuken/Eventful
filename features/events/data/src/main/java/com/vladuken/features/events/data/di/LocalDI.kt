package com.vladuken.features.events.data.di

import android.content.Context
import androidx.room.Room
import com.vladuken.features.events.data.local.EventfullRoomDb

fun provideDatabase(context: Context) = Room.databaseBuilder(
    context,
    EventfullRoomDb::class.java, "eventful-database"
)
    .fallbackToDestructiveMigration()
    .build()