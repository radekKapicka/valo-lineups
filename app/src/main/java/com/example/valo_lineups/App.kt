package com.example.valo_lineups

import android.app.Application
import com.example.valo_lineups.di.dataModule

import com.example.valo_lineups.di.uiModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(applicationContext)
            modules(listOf(dataModule))
            modules(listOf(uiModule))
            FirebaseApp.initializeApp(getApplicationContext());
        }
    }

}