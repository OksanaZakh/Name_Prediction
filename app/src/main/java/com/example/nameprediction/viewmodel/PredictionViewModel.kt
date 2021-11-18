package com.example.nameprediction.viewmodel

import androidx.lifecycle.*
import com.example.nameprediction.data.Prediction
import com.example.nameprediction.source.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PredictionViewModel(private val repository: Repository) : ViewModel() {

    private val _prediction = MutableLiveData<Prediction?>()

    val prediction: LiveData<Prediction?>
        get() = _prediction

    private val _isLoading = MutableLiveData(false)

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _snackBar = MutableLiveData<String?>()

    val snackBar: LiveData<String?>
        get() = _snackBar

    fun getDataFromRepo(name: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = withContext(Dispatchers.Default) {
                repository.getPerson(name)
            }
            if (response.isSuccessful) {
                _prediction.value = response.body()
            } else {
                _snackBar.value = response.message()
            }
        }
        _isLoading.value = false
    }

    fun saveLastName() {
        viewModelScope.launch {
            prediction.value?.let {
                repository.saveName(it.name)
            }
        }
    }

    fun onSnackBarShown() {
        _snackBar.value = null
    }
}