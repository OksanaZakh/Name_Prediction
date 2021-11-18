package com.example.nameprediction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nameprediction.model.Person
import com.example.nameprediction.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PredictionViewModel(private val repository: Repository) : ViewModel() {

    private val _person = MutableLiveData<Person?>()

    val person: LiveData<Person?>
        get() = _person

    private val _isLoading = MutableLiveData(false)

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _snackBar = MutableLiveData<String?>()

    val snackBar: LiveData<String?>
        get() = _snackBar


    fun onGetDataButtonClicked(name: String) {
        getDataFromRepo(name)
    }

    private fun getDataFromRepo(name: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getPerson(name)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _person.value = response.body()
                } else {
                    _snackBar.value = response.message()
                }
            }
            _isLoading.value = false
        }
    }

    fun onSnackBarShown() {
        _snackBar.value = null
    }

//    private fun launchDataLoad(block: suspend () -> Unit) {
//        viewModelScope.launch {
//            try {
//                _isLoading.value = true
//                block()
//            } catch (exception: Exception) {
//                _snackBar.value = exception.message
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
}