package com.example.nameprediction.source

import android.content.Context

class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext

    suspend fun savePersonNameToPref(name: String) {
        appContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit()
            .putString(KEY_NAME, name).apply()
    }

    suspend fun getNameFromPref(): String? {
        return appContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            .getString(KEY_NAME, null)
    }

    companion object {
        const val FILE_NAME = "name_prediction"
        const val KEY_NAME = "key_name"
    }

}