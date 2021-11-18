package com.example.nameprediction.source

import com.example.nameprediction.data.Prediction
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkDataProvider {

    @GET(".")
    suspend fun getPersonData(@Query("name") category: String): Response<Prediction?>

    companion object {
        var networkDataProvider: NetworkDataProvider? = null

        fun getInstance(): NetworkDataProvider {
            if (networkDataProvider == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.agify.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                networkDataProvider = retrofit.create(NetworkDataProvider::class.java)
            }
            return networkDataProvider!!
        }
    }
}