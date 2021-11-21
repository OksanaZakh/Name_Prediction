package com.example.nameprediction.source

class RepositoryImpl(
    private val networkDataProvider: NetworkDataProvider,
    private val preferenceProvider: PreferenceProvider
): Repository {

    override suspend fun getPerson(name: String) = networkDataProvider.getPersonData(name)

    override fun getStoredNameIfAny() = preferenceProvider.getNameFromPref()

    override fun saveName(name: String) = preferenceProvider.savePersonNameToPref(name)
}