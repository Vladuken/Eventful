package com.vladuken.eventful

import android.app.Application
import com.vladuken.features.events.presentation.di.eventFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class EventfulApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EventfulApp)
            modules(eventFeatureModule(BuildConfig.BASE_URL))
        }
    }

}