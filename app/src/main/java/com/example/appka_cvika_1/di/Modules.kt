package com.example.appka_cvika_1.di

import com.example.appka_cvika_1.data.remote.service.SpaceXAPIService
import com.example.appka_cvika_1.data.repository.SpeceXRepository
import com.example.appka_cvika_1.ui.async.launches.RocketLaunchesViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import retrofit2.Retrofit

val uiModule = module {
    viewModel { RocketLaunchesViewModel(get()) }
}

val dataModule = module {
    single{ createSpaceXService() }
    single{ SpeceXRepository(get()) }
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