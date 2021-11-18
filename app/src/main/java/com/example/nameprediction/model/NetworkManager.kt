package com.example.nameprediction.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class NetworkManager {
//
//    var retrofitService: APIService =
//        RetrofitInstance.getRetrofitInstance().create(APIService::class.java)
//
//    suspend fun loadPersonData(name: String) {
//        retrofitService.getPersonData(name).enqueue(object : Callback<Person?> {
//            override fun onResponse(call: Call<Person?>, response: Response<Person?>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<Person?>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }
//}
