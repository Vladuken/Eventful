package com.vladuken.features.events.presentation.di

import com.vladuken.features.events.data.local.usecases.FetchAndCacheEventsUseCase
import com.vladuken.features.events.data.local.usecases.RoomCacheEventsUseCase
import com.vladuken.features.events.data.local.usecases.RoomFetchEventsUseCase
import com.vladuken.features.events.data.network.usecases.NetworkFetchEventsUseCase
import com.vladuken.features.events.domain.usecases.CacheEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal val useCaseModule = module {
    single<FetchEventsUseCase>(named(StorageQualifier.NETWORK)) {
        NetworkFetchEventsUseCase(eventApi = get())
    }

    single<FetchEventsUseCase>(named(StorageQualifier.LOCAL)) {
        RoomFetchEventsUseCase(eventDao = get())
    }

    single<CacheEventsUseCase> {
        RoomCacheEventsUseCase(eventDao = get())
    }

    single<FetchEventsUseCase> {
        FetchAndCacheEventsUseCase(
            networkFetchEventsUseCase = get(named(StorageQualifier.NETWORK)),
            localFetchEventsUseCase = get(named(StorageQualifier.LOCAL)),
            cacheEventsUseCase = get()
        )
    }


}
