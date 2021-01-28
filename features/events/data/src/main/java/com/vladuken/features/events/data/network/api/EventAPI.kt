package com.vladuken.features.events.data.network.api

import com.vladuken.features.events.data.network.model.SearchEventsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EventAPI {

    @GET("/json/events/search")
    suspend fun getEvents(
        @Query("app_key") appKey: String,
        @Query("keywords") keyWords: String,
        @Query("location") locationString: String,
        @Query("date") dateString: String,
        @Query("page_size") pageSize: Int
    ): SearchEventsDto

}