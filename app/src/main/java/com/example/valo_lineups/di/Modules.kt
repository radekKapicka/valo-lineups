package com.example.valo_lineups.di

import androidx.room.Room
import com.example.valo_lineups.data.remote.service.APIConfig

import com.example.valo_lineups.data.remote.service.ValoAPIService
import com.example.valo_lineups.data.repository.ValoRepository
import com.example.valo_lineups.ui.async.agents.AgentAPIViewModel
import com.example.valo_lineups.ui.async.lineups.LineupsScreen

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModules by lazy { listOf(dataModule, uiModule) }

val dataModule = module {
    apiServices()
    repositories()
}


val uiModule = module {
    viewModels()
}

private fun Module.viewModels() {
    viewModel { (agentId: String) -> AgentAPIViewModel(agentId, get()) }
}

private fun Module.repositories() {
    single { ValoRepository(get()) }
}

private fun Module.apiServices() {
    single { createRetrofit(createOkHttpClient(), APIConfig.BaseApiUrl) }
    single { get<Retrofit>().create(ValoAPIService::class.java) }
}


private val json = Json {
    ignoreUnknownKeys = true
}

@OptIn(ExperimentalSerializationApi::class)
private fun createRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String,
): Retrofit {
    return Retrofit.Builder().apply {
        client(okHttpClient)
        baseUrl(baseUrl)
        addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    }.build()
}

private fun createOkHttpClient() = OkHttpClient.Builder().build()




