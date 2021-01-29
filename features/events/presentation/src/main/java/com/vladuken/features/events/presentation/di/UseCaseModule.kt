package com.vladuken.features.events.presentation.di

import android.content.Context
import com.vladuken.features.events.data.local.usecases.*
import com.vladuken.features.events.data.network.usecases.NetworkFetchEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.CacheEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.ClearCacheEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.UpdateCacheEventUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal val useCaseModule = module {

    single<CacheEventsUseCase> {
        RoomCacheEventsUseCase(eventDao = get())
    }

    single<ClearCacheEventsUseCase> {
        RoomClearCacheEventsUseCase(eventDao = get())
    }

    single<UpdateCacheEventUseCase> {
        RoomUpdateCacheEventUseCase(favoriteEventDao = get())
    }

    //FetchEventsUseCases

    single<FetchEventsUseCase>(named(FetchEventsUseCase.FetchType.NETWORK)) {
        NetworkFetchEventsUseCase(eventApi = get())
    }

    single<FetchEventsUseCase>(named(FetchEventsUseCase.FetchType.LOCAL)) {
        RoomFetchEventsUseCase(eventDao = get(), favoriteEventDao = get())
    }

    single<FetchEventsUseCase>(named(FetchEventsUseCase.FetchType.LOCAL_AND_NETWORK)) {
        FetchAndCacheEventsUseCase(
            networkFetchEventsUseCase = get(named(FetchEventsUseCase.FetchType.NETWORK)),
            localFetchEventsUseCase = get(named(FetchEventsUseCase.FetchType.LOCAL)),
            cacheEventsUseCase = get(),
        )
    }

    single<FetchEventsUseCase>(named(FetchEventsUseCase.FetchType.CLEAR_AFTER_ONE_HOUR)) {
        ClearCacheAfterTimeAndFetchEventsUseCase(
            fetchEventsUseCase = get(named(FetchEventsUseCase.FetchType.LOCAL_AND_NETWORK)),
            clearCacheEventsUseCase = get(),
            sharedPreferences = androidApplication().getSharedPreferences(
                "FetchAndCacheEventsUseCase",
                Context.MODE_PRIVATE
            )
        )
    }

}
