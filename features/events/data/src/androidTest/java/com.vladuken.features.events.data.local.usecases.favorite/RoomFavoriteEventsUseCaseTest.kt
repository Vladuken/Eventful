package com.vladuken.features.events.data.local.usecases.favorite

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vladuken.features.events.data.local.EventfullRoomDb
import com.vladuken.features.events.data.mocks.EventMocks
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RoomFavoriteEventsUseCaseTest {

    private lateinit var fetchFavoriteEventUseCase: RoomFetchFavoriteEventsUseCase
    private lateinit var toggleFavoriteEventUseCase: RoomToggleFavoriteEventUseCase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val dao = Room.inMemoryDatabaseBuilder(context, EventfullRoomDb::class.java)
            .build()
            .favoriteEventDao()

        fetchFavoriteEventUseCase = RoomFetchFavoriteEventsUseCase(dao)
        toggleFavoriteEventUseCase = RoomToggleFavoriteEventUseCase(dao)

    }

    @Test
    @Throws(Exception::class)
    fun checkInitial() = runBlocking {
        val testEvent = EventMocks.randomEvent()

        assertFalse(fetchFavoriteEventUseCase().contains(testEvent))
    }

    @Test
    @Throws(Exception::class)
    fun checkToggleElement() = runBlocking {
        val testEvent = EventMocks.randomEvent()

        toggleFavoriteEventUseCase(testEvent)

        assertTrue(fetchFavoriteEventUseCase().contains(testEvent))
    }

    @Test
    @Throws(Exception::class)
    fun checkToggleElementTwice() = runBlocking {
        val testEvent = EventMocks.randomEvent()

        toggleFavoriteEventUseCase(testEvent)
        toggleFavoriteEventUseCase(testEvent)

        assertFalse(fetchFavoriteEventUseCase().contains(testEvent))
    }

    @Test
    @Throws(Exception::class)
    fun checkToggleElementThreeTimes() = runBlocking {
        val testEvent = EventMocks.randomEvent()

        toggleFavoriteEventUseCase(testEvent)
        toggleFavoriteEventUseCase(testEvent)
        toggleFavoriteEventUseCase(testEvent)

        assertTrue(fetchFavoriteEventUseCase().contains(testEvent))
    }


    @Test
    @Throws(Exception::class)
    fun checkToggleMultipleElements() = runBlocking {
        val testEvents = EventMocks.listOfRandomEvents(5)
        testEvents.forEach { toggleFavoriteEventUseCase(it) }

        val favoriteEvents = fetchFavoriteEventUseCase()
        assertArrayEquals(testEvents.toTypedArray(), favoriteEvents.toTypedArray())
    }

    @Test
    @Throws(Exception::class)
    fun checkToggleMultipleElementsTwice() = runBlocking {
        val testEvents = EventMocks.listOfRandomEvents(5)
        testEvents.forEach { toggleFavoriteEventUseCase(it) }
        testEvents.forEach { toggleFavoriteEventUseCase(it) }

        val favoriteEvents = fetchFavoriteEventUseCase()

        assertTrue(favoriteEvents.isEmpty())
    }

}