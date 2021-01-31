package com.vladuken.features.events.data.local.usecases.recent

import android.content.SharedPreferences
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.domain.usecases.FetchRecentEventsUseCase
import com.vladuken.features.events.domain.usecases.cache.ClearCacheRecentEventsUseCase
import java.util.*

private const val SHARED_PREFERENCE_DATE_KEY =
    "FetchAndCacheEventsUseCase_SHARED_PREFERENCE_DATE_KEY"
private const val DATE_ONE_HOUR = 3600 * 1000

class ClearCacheAfterTimeAndFetchRecentEventsUseCase(
    private val fetchRecentEventsUseCase: FetchRecentEventsUseCase,
    private val clearCacheRecentEventsUseCase: ClearCacheRecentEventsUseCase,
    private val sharedPreferences: SharedPreferences
) : FetchRecentEventsUseCase {

    override suspend fun invoke(): List<Event> {
        val storedDate = getStoredDate()
        val currentDate = Date()

        if (storedDate.time + DATE_ONE_HOUR < currentDate.time) {
            clearCacheRecentEventsUseCase()
            storeDate(currentDate)
        }

        return fetchRecentEventsUseCase()
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