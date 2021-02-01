package com.vladuken.features.events.data.local.usecases.recent

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vladuken.features.events.data.local.EventfullRoomDb
import com.vladuken.features.events.data.mocks.EventMocks
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.random.Random

class RoomCacheRecentEventsUseCaseTest {

    private lateinit var fetchCachedRecentEventUseCase: RoomFetchRecentEventsUseCase
    private lateinit var cacheRecentEventUseCase: RoomCacheRecentEventsUseCase
    private lateinit var clearCacheRecentEventsUseCase: RoomClearCacheRecentEventsUseCase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val dao = Room.inMemoryDatabaseBuilder(context, EventfullRoomDb::class.java)
            .build()
            .eventDao()

        fetchCachedRecentEventUseCase = RoomFetchRecentEventsUseCase(dao)
        cacheRecentEventUseCase = RoomCacheRecentEventsUseCase(dao)
        clearCacheRecentEventsUseCase = RoomClearCacheRecentEventsUseCase(dao)
    }

    @Test
    @Throws(Exception::class)
    fun checkInitial() = runBlocking {
        assertTrue(fetchCachedRecentEventUseCase().isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun checkClearCache() = runBlocking {
        clearCacheRecentEventsUseCase()
        assertTrue(fetchCachedRecentEventUseCase().isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun checkCacheEmptyList() = runBlocking {
        cacheRecentEventUseCase(emptyList())
        assertTrue(fetchCachedRecentEventUseCase().isEmpty())
    }


    @Test
    @Throws(Exception::class)
    fun checkCacheOneItem() = runBlocking {
        val recentEvents = EventMocks.listOfRandomEvents(1)
        cacheRecentEventUseCase(recentEvents)
        assertArrayEquals(
            recentEvents.toTypedArray(),
            fetchCachedRecentEventUseCase().toTypedArray()
        )
    }

    @Test
    @Throws(Exception::class)
    fun checkCacheList() = runBlocking {
        val recentEvents = EventMocks.listOfRandomEvents(15)
        cacheRecentEventUseCase(recentEvents)
        assertArrayEquals(
            recentEvents.toTypedArray(),
            fetchCachedRecentEventUseCase().toTypedArray()
        )
    }

    @Test
    @Throws(Exception::class)
    fun checkCacheSameListMultipleTimes() = runBlocking {
        val recentEvents = EventMocks.listOfRandomEvents(15)
        cacheRecentEventUseCase(recentEvents)
        cacheRecentEventUseCase(recentEvents)
        assertArrayEquals(
            recentEvents.toTypedArray(),
            fetchCachedRecentEventUseCase().toTypedArray()
        )
    }

    @Test
    @Throws(Exception::class)
    fun checkCacheSameListMultipleTimesButWithNewDate() = runBlocking {
        val recentEvents = EventMocks.listOfRandomEvents(15)
        val recentEventsModified = recentEvents.map {
            it.copy(date = Date(Random.nextLong()))
        }

        cacheRecentEventUseCase(recentEvents)
        cacheRecentEventUseCase(recentEventsModified)
        assertArrayEquals(
            recentEventsModified.toTypedArray(),
            fetchCachedRecentEventUseCase().toTypedArray()
        )
    }


    @Test
    @Throws(Exception::class)
    fun checkCacheAndClear() = runBlocking {
        val recentEvents = EventMocks.listOfRandomEvents(15)
        cacheRecentEventUseCase(recentEvents)
        clearCacheRecentEventsUseCase()
        assertTrue(fetchCachedRecentEventUseCase().isEmpty())
    }


}