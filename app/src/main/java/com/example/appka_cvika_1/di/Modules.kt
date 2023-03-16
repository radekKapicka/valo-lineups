package com.example.appka_cvika_1.di

import androidx.room.Room
import com.example.appka_cvika_1.data.database.AppDatabase
import com.example.appka_cvika_1.data.remote.service.SpaceXAPIService
import com.example.appka_cvika_1.data.repository.SpeceXRepository
import com.example.appka_cvika_1.ui.async.launches.RocketLaunchesViewModel
import com.example.appka_cvika_1.ui.async.rocketDetail.RocketDetailViewModel
import com.example.appka_cvika_1.ui.database.DatabaseViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import retrofit2.Retrofit

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = AppDatabase::class.java,
            name = AppDatabase.Name
        ).build()
    }
    single {
        get<AppDatabase>().noteDAO()
    }
}

val uiModule = module {
    viewModel { RocketLaunchesViewModel(get()) }
    viewModel { (rocketId: String) -> RocketDetailViewModel(rocketId, get()) }
    viewModel { DatabaseViewModel(get()) }
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