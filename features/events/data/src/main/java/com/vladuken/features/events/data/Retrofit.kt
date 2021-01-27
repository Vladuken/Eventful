package com.vladuken.features.events.data

import com.vladuken.features.events.data.api.EventAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//TODO Add DI
private const val BASE_URL = "http://api.eventful.com/"

private val client =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

private fun provideRetrofit(
    baseUrl: String,
    client: OkHttpClient
) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val api = provideRetrofit(BASE_URL, client)
    .create(EventAPI::class.java)