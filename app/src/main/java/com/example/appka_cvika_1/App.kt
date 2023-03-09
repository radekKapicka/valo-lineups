package com.example.appka_cvika_1

import android.app.Application
import com.example.appka_cvika_1.di.dataModule
import com.example.appka_cvika_1.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(applicationContext)
            modules(listOf(dataModule))
            modules(listOf(uiModule))
        }
    }

}