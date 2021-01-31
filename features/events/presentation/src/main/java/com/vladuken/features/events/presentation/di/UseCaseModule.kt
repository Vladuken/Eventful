package com.vladuken.features.events.presentation.di

import android.content.Context
import com.vladuken.features.events.data.local.usecases.favorite.RoomFetchFavoriteEventsUseCase
import com.vladuken.features.events.data.local.usecases.favorite.RoomFetchRecentEventsUseCase
import com.vladuken.features.events.data.local.usecases.favorite.RoomToggleFavoriteEventUseCase
import com.vladuken.features.events.data.local.usecases.recent.ClearCacheAfterTimeAndFetchRecentEventsUseCase
import com.vladuken.features.events.data.local.usecases.recent.FetchAndCacheRecentEventsUseCase
import com.vladuken.features.events.data.local.usecases.recent.RoomCacheRecentEventsUseCase
import com.vladuken.features.events.data.local.usecases.recent.RoomClearCacheRecentEventsUseCase
import com.vladuken.features.events.data.network.usecases.NetworkFetchRecentEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchFavoriteEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchRecentEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.CacheRecentEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.ClearCacheRecentEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.ToggleFavoriteEventUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal val useCaseModule = module {

    //FavoriteEvents
    single<FetchFavoriteEventsUseCase> {
        RoomFetchFavoriteEventsUseCase(
            eventFavoriteEventDao = get()
        )
    }

    single<ToggleFavoriteEventUseCase> {
        RoomToggleFavoriteEventUseCase(
            eventDao = get()
        )
    }

    //RecentEvents
    single<CacheRecentEventsUseCase> {
        RoomCacheRecentEventsUseCase(
            eventDao = get()
        )
    }

    single<ClearCacheRecentEventsUseCase> {
        RoomClearCacheRecentEventsUseCase(
            eventDao = get()
        )
    }

    single<FetchRecentEventsUseCase>(named(FetchRecentEventsUseCase.FetchType.NETWORK)) {
        NetworkFetchRecentEventsUseCase(eventApi = get())
    }

    single<FetchRecentEventsUseCase>(named(FetchRecentEventsUseCase.FetchType.LOCAL)) {
        RoomFetchRecentEventsUseCase(
            eventDao = get()
        )
    }

    single<FetchRecentEventsUseCase>(named(FetchRecentEventsUseCase.FetchType.LOCAL_AND_NETWORK)) {
        FetchAndCacheRecentEventsUseCase(
            networkFetchRecentEventsUseCase = get(named(FetchRecentEventsUseCase.FetchType.NETWORK)),
            localFetchRecentEventsUseCase = get(named(FetchRecentEventsUseCase.FetchType.LOCAL)),
            cacheRecentEventsUseCase = get(),
        )
    }

    single<FetchRecentEventsUseCase>(named(FetchRecentEventsUseCase.FetchType.CLEAR_AFTER_ONE_HOUR)) {
        ClearCacheAfterTimeAndFetchRecentEventsUseCase(
            fetchRecentEventsUseCase = get(named(FetchRecentEventsUseCase.FetchType.LOCAL_AND_NETWORK)),
            clearCacheRecentEventsUseCase = get(),
            sharedPreferences = androidApplication().getSharedPreferences(
                "FetchAndCacheEventsUseCase",
                Context.MODE_PRIVATE
            )
        )
    }

}
