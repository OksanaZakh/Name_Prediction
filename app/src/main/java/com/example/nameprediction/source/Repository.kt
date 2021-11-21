package com.example.nameprediction.source

import com.example.nameprediction.data.Prediction
import retrofit2.Response

interface Repository {

    suspend fun getPerson(name: String): Response<Prediction?>

    fun getStoredNameIfAny(): String?

    fun saveName(name: String)
}