package com.vladuken.features.events.presentation.di

import com.vladuken.features.events.data.local.usecases.RoomCachePagedEventsUseCase
import com.vladuken.features.events.data.local.usecases.RoomFetchPagedEventsUseCase
import com.vladuken.features.events.data.usecases.NetworkFetchPagedEventsUseCase
import com.vladuken.features.events.domain.usecases.CachePagedEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchPagedEventsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal val useCaseModule = module {
    single<FetchPagedEventsUseCase>(named(StorageQualifier.NETWORK)) {
        NetworkFetchPagedEventsUseCase(eventApi = get())
    }

    single<FetchPagedEventsUseCase>(named(StorageQualifier.LOCAL)) {
        RoomFetchPagedEventsUseCase(roomEventDao = get())
    }

    single<CachePagedEventsUseCase> {
        RoomCachePagedEventsUseCase(roomEventDao = get())
    }
}
