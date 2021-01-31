package com.vladuken.features.events.presentation.di


import com.vladuken.features.events.data.di.provideDatabase
import com.vladuken.features.events.data.local.EventfullRoomDb
import com.vladuken.features.events.data.local.dao.RoomEventDao
import com.vladuken.features.events.data.local.dao.RoomFavoriteEventDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal val roomModule = module {
    single<EventfullRoomDb> { provideDatabase(androidApplication()) }

    single<RoomEventDao> { get<EventfullRoomDb>().eventDao() }

    single<RoomFavoriteEventDao> { get<EventfullRoomDb>().favoriteEventDao() }
}

