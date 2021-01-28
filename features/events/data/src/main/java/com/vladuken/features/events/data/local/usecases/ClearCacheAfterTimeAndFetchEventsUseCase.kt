package com.vladuken.features.events.data.local.usecases

import android.content.SharedPreferences
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.ClearCacheEventsUseCase
import java.util.*

private const val SHARED_PREFERENCE_DATE_KEY =
    "FetchAndCacheEventsUseCase_SHARED_PREFERENCE_DATE_KEY"
private const val DATE_ONE_HOUR = 3600 * 1000

class ClearCacheAfterTimeAndFetchEventsUseCase(
    private val fetchEventsUseCase: FetchEventsUseCase,
    private val clearCacheEventsUseCase: ClearCacheEventsUseCase,
    private val sharedPreferences: SharedPreferences
) : FetchEventsUseCase {

    override suspend fun invoke(): List<Event> {
        val storedDate = getStoredDate()
        val currentDate = Date()

        if (storedDate.time + DATE_ONE_HOUR < currentDate.time) {
            clearCacheEventsUseCase()
            storeDate(currentDate)
        }

        return fetchEventsUseCase()
    }

    private fun getStoredDate(): Date {
        val dateLong = sharedPreferences
            .getLong(SHARED_PREFERENCE_DATE_KEY, 0)

        return Date(dateLong)
    }

    private fun storeDate(date: Date) {
        sharedPreferences
            .edit()
            .putLong(SHARED_PREFERENCE_DATE_KEY, date.time)
            .apply()
    }
    
}