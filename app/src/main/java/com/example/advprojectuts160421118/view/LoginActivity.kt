package com.example.advprojectuts160421118.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advprojectuts160421118.R
import com.example.advprojectuts160421118.databinding.ActivityLoginBinding
import com.example.advprojectuts160421118.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            loginViewModel.loginUser(username, password)
        }

        loginViewModel.loginSuccess.observe(this, Observer { success ->
            if (success) {
                // Redirect ke layar utama atau halaman setelah login berhasil
            } else {
                // Tampilkan pesan error kepada pengguna bahwa login gagal
            }
        })
    }
}