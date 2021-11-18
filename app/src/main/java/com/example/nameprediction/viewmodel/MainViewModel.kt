package com.example.nameprediction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nameprediction.source.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _name = MutableLiveData<String?>()

    val name: LiveData<String?>
        get() = _name

    fun getNameFromSharedPref() {
        viewModelScope.launch {
            _name.value = repository.getStoredNameIfAny()
        }
    }
}