package com.vladuken.features.events.presentation.di

import com.vladuken.features.events.data.usecases.NetworkFetchEventsUseCase
import com.vladuken.features.events.domain.models.usecases.FetchEventsUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    single<FetchEventsUseCase> { NetworkFetchEventsUseCase(eventApi = get()) }
}
