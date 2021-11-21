package com.example.nameprediction.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin


class PredictionApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PredictionApplication)
            androidLogger()
            loadKoinModules(listOf(appModule))
        }
    }
}