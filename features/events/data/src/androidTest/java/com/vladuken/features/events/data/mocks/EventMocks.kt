package com.vladuken.features.events.data.mocks

import com.vladuken.features.events.domain.models.Event
import java.util.*
import kotlin.random.Random

object EventMocks {

    fun randomEvent() = Event(
        id = Random.nextInt().toString(),
        title = Random.nextInt().toString(),
        date = Date(Random.nextLong()),
        url = Random.nextInt().toString()
    )

    fun listOfRandomEvents(n: Int): List<Event> {
        val list = mutableListOf<Event>()
        for (i in 1..n) {
            list.add(randomEvent())
        }
        return list
    }
}