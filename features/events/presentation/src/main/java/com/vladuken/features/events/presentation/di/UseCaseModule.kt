package com.vladuken.features.events.presentation.di

import com.vladuken.features.events.data.usecases.NetworkFetchPagedEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchPagedEventsUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    single<FetchPagedEventsUseCase> { NetworkFetchPagedEventsUseCase(eventApi = get()) }
}
