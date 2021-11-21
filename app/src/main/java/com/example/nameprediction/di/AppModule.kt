package com.example.nameprediction.di

import com.example.nameprediction.source.NetworkDataProvider
import com.example.nameprediction.source.PreferenceProvider
import com.example.nameprediction.source.Repository
import com.example.nameprediction.source.RepositoryImpl
import com.example.nameprediction.viewmodel.MainViewModel
import com.example.nameprediction.viewmodel.PredictionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModule = module {
    single { PreferenceProvider(androidContext()) }
    single { NetworkDataProvider.getInstance() }
    single<Repository> { RepositoryImpl(get(), get()) }
    viewModel { MainViewModel(get()) }
    viewModel { PredictionViewModel(get()) }
}