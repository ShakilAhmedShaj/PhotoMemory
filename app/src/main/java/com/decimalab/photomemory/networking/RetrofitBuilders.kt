package com.decimalab.photomemory.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Builds Retrofit and its components.
 */

private const val BASE_URL = "http://<your-ip>:3000"

fun buildApiService(): RemoteApiService = buildRetrofit().create(RemoteApiService::class.java)

fun buildRetrofit(): Retrofit {
  val contentType = "application/json".toMediaType()

  return Retrofit.Builder()
      .client(buildClient())
      .baseUrl(BASE_URL)
      .addConverterFactory(Json.nonstrict.asConverterFactory(contentType))
      .build()
}

fun buildClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    })
    .build()
