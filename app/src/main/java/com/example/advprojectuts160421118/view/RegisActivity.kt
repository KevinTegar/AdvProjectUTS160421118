package com.example.advprojectuts160421118.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advprojectuts160421118.R
import com.example.advprojectuts160421118.databinding.ActivityLoginBinding
import com.example.advprojectuts160421118.databinding.ActivityRegisBinding
import com.example.advprojectuts160421118.viewmodel.RegistViewModel

class RegisActivity : AppCompatActivity() {
    private lateinit var registerViewModel: RegistViewModel
    private lateinit var  binding: ActivityRegisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisBinding.inflate(layoutInflater)
        val view = binding.root
        registerViewModel = ViewModelProvider(this).get(RegistViewModel::class.java)

        binding.btnRegister.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val firstName = binding.txtFirstName.text.toString()
            val lastName = binding.txtLastName.text.toString()
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            val confirmPassword = binding.txtConfPassword.text.toString()

            if (password == confirmPassword) {
                registerViewModel.registerUser(username, firstName, lastName, email, password)
            } else {
                // Tampilkan pesan kepada pengguna bahwa password dan konfirmasi password tidak cocok
            }
        }

        registerViewModel.registrationSuccess.observe(this, Observer { success ->
            if (success) {
                // Redirect ke layar login atau halaman setelah registrasi berhasil
            } else {
                // Tampilkan pesan error kepada pengguna bahwa registrasi gagal
            }
        })
    }
}