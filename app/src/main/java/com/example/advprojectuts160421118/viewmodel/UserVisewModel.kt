package com.example.advprojectuts160421118.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advprojectuts160421118.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserVisewModel (application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<ArrayList<User>?>()
    val regisLD: MutableLiveData<Boolean> = MutableLiveData()
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
    fun regisUser(username: String, namaDepan: String, namaBelakang: String, email: String, password: String) {
        queue = Volley.newRequestQueue(getApplication())

        val url = "http://10.0.2.2/hobby/register.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                try {
                    Log.d("showVolley", "Registration response: $response")
                    // Perbarui registrationResult dengan true jika registrasi berhasil
                    regisLD.value = true
                } catch (e: Exception) {
                    Log.d("showVolley", "Error parsing registration response: ${e.message}")
                }
            },
            { error ->
                Log.d("showVolley", "Error registering user: ${error.message}")
                Toast.makeText(getApplication(), "Network error", Toast.LENGTH_SHORT).show()
                // Perbarui registrationResult dengan false jika terjadi error
                regisLD.value = false
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["nama_depan"] = namaDepan
                params["nama_belakang"] = namaBelakang
                params["email"] = email
                params["password"] = password
                return params
            }
        }
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