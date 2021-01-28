package com.vladuken.features.events.presentation.di

import com.vladuken.features.events.data.network.usecases.NetworkFetchEventsUseCase
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase
import org.koin.dsl.module


internal val useCaseModule = module {
    single<FetchEventsUseCase> {
        NetworkFetchEventsUseCase(eventApi = get())
    }
}
