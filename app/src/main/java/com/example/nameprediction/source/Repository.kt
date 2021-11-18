package com.example.nameprediction.source

class Repository(
    private val networkDataProvider: NetworkDataProvider,
    val preferenceProvider: PreferenceProvider
) {

    suspend fun getPerson(name: String) = networkDataProvider.getPersonData(name)

    suspend fun getStoredNameIfAny() = preferenceProvider.getNameFromPref()

    suspend fun saveName(name: String) = preferenceProvider.savePersonNameToPref(name)
}