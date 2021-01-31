package com.vladuken.features.events.presentation.model.itemcallbacks.entity

import com.vladuken.features.events.domain.models.Event

data class FavoriteEvent(
    val event: Event,
    val isFavorite: Boolean
)