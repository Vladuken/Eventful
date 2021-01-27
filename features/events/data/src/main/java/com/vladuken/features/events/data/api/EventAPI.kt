package com.vladuken.features.events.data.api

import com.vladuken.features.events.data.model.SearchEventsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EventAPI {

    @GET("/json/events/search")
    suspend fun getEvents(
        @Query("app_key") appKey: String,
        @Query("keywords") keyWords: String,
        @Query("location") locationString: String,
        @Query("date") dateString: String
    ): SearchEventsDto

}