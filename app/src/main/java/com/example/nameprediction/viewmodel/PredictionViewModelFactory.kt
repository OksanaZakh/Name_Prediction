package com.example.nameprediction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PredictionViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PredictionViewModel::class.java)) {
            return PredictionViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}