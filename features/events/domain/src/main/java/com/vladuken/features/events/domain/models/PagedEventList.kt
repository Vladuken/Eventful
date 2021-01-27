package com.vladuken.features.events.domain.models

data class PagedEventList(
    val events: List<Event>,
    val currentPage: Int,
    val pageCount: Int
)