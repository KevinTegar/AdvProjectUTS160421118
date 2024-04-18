package com.example.advprojectuts160421118.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegistViewModel (application: Application): AndroidViewModel(application) {
    val registrationSuccess = MutableLiveData<Boolean>()

    fun registerUser(username: String, firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            // Panggil fungsi repository untuk melakukan registrasi
            val isRegistered = repository.registerUser(username, firstName, lastName, email, password)
            registrationSuccess.postValue(isRegistered)
        }
    }
}