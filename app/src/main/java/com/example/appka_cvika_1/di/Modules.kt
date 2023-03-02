package com.example.appka_cvika_1.di

import com.example.appka_cvika_1.data.remote.service.SpaceXAPIService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit


val dataModule = module {
    single{ createSpaceXService() }
}

private val json = Json {
    ignoreUnknownKeys = true
}

fun createSpaceXService() = createRetrofit().create(SpaceXAPIService::class.java)

//kotlin zápis fce
fun createRetrofit() = Retrofit.Builder().apply {
        client(OkHttpClient.Builder().build())
        baseUrl("https://api.spacexdata.com/v3/")
        addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
    }.build()