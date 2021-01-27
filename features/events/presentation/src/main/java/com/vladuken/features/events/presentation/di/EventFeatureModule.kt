package com.vladuken.features.events.presentation.di

fun eventFeatureModule(baseUrl: String) = viewModelModule +
        networkModule(baseUrl = baseUrl) +
        useCaseModule +
        roomModule

