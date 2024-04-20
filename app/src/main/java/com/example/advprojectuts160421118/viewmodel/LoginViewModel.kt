package com.example.advprojectuts160421118.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advprojectuts160421118.model.Hobby
import com.example.advprojectuts160421118.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginViewModel (application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<ArrayList<User>?>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun loginUser(username: String, password: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/hobby/login.php?username=" + username + "&password=" + password
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                try {
                    val sType = object : TypeToken<List<User>>() { }.type
                    val result = Gson().fromJson<List<User>>(it, sType)
                    if (result.isNotEmpty()) {
                        userLD.value = result as ArrayList<User>?
                    }
                    Toast.makeText(getApplication(), "Login berhasil", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing response: ${e.message}")
                    // Handle parsing error
                    Toast.makeText(getApplication(), "Akun belum dibuat,mohon registrasi dahulu", Toast.LENGTH_SHORT).show()
                }
            },
            {
                Log.d("showvoley", it.toString())
                Toast.makeText(getApplication(), "An error occurred, please try again later", Toast.LENGTH_SHORT).show()
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
    fun regisUser(username: String, password: String, namaDepan: String, namaBelakang: String,email: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/hobby/register.php?username=" + username + "&password=" + password + "&nama_depan=" +
                namaDepan + "&nama_belakang=" + namaBelakang + "&email=" + email
        val stringRequest = StringRequest(
            Request.Method.POST, url,
            {
                val sType = object : TypeToken<List<User>>() { }.type
                val result = Gson().fromJson<List<User>>(it, sType)
                userLD.value = result as ArrayList<User>?
            },
            {
                Log.d("showvoley", it.toString())

            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
    fun clearUser() {
        userLD.value = null
    }
}