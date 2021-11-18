package com.example.nameprediction.model

class Repository constructor(private val retrofitService: RetrofitService) {

    suspend fun getPerson(name: String) = retrofitService.getPersonData(name)

}