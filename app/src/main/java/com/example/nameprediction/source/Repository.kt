package com.example.nameprediction.source

class Repository(
    private val networkDataProvider: NetworkDataProvider,
    private val preferenceProvider: PreferenceProvider
) {

    suspend fun getPerson(name: String) = networkDataProvider.getPersonData(name)

    fun getStoredNameIfAny() = preferenceProvider.getNameFromPref()

    fun saveName(name: String) = preferenceProvider.savePersonNameToPref(name)
}