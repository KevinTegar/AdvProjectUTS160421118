package com.example.advprojectuts160421118.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel (application: Application): AndroidViewModel(application) {
    val loginSuccess = MutableLiveData<Boolean>()

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            // Panggil fungsi repository untuk melakukan login
            val isLoggedIn = repository.loginUser(username, password)
            loginSuccess.postValue(isLoggedIn)
        }
    }
}