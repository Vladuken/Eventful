package com.vladuken.features.events.data.di

import com.google.gson.GsonBuilder
import com.vladuken.features.events.data.api.EventAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val DATE_FORMAT = "yyyy-mm-dd HH:mm:ss"

fun provideRetrofit(
    baseUrl: String,
    client: OkHttpClient,
) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(client)
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create()
        )
    )
    .build()

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

fun provideEventsApi(retrofit: Retrofit): EventAPI =
    retrofit.create(EventAPI::class.java)
