package com.example.valo_lineups.di

import androidx.room.Room

import com.example.valo_lineups.data.remote.service.ValoAPIService
import com.example.valo_lineups.data.repository.ValoRepository
import com.example.valo_lineups.ui.async.launches.MapsViewModel

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val uiModule = module {
    viewModel { MapsViewModel(get()) }
}

val dataModule = module {
    single { ValoRepository(get()) }
    single { createMapsService() }
}

private val json = Json {
    ignoreUnknownKeys = true
}

fun createMapsService() = createRetrofit().create(ValoAPIService::class.java)


//kotlin zápis fce
fun createRetrofit() = Retrofit.Builder().apply {
        client(OkHttpClient.Builder().build())
        baseUrl("https://valorant-api.com/v1/")
    //addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        addConverterFactory(GsonConverterFactory.create())
    }.build()

