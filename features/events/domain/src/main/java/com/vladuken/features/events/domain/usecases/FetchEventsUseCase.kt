package com.vladuken.features.events.domain.usecases

import com.vladuken.features.events.domain.models.Event

interface FetchEventsUseCase {
    suspend operator fun invoke(): List<Event>

    enum class FetchType {
        LOCAL, NETWORK, LOCAL_AND_NETWORK, CLEAR_AFTER_ONE_HOUR
    }

}