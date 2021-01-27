package com.vladuken.features.events.presentation.di


import com.vladuken.features.events.data.di.provideEventsApi
import com.vladuken.features.events.data.di.provideOkHttpClient
import com.vladuken.features.events.data.di.provideRetrofit
import org.koin.dsl.module

internal fun networkModule(baseUrl: String) = module {
    factory { provideOkHttpClient() }
    factory { provideEventsApi(retrofit = get()) }
    single { provideRetrofit(baseUrl = baseUrl, client = get()) }
}

