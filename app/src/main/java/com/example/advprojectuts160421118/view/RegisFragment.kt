package com.example.advprojectuts160421118.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import com.example.advprojectuts160421118.R
import com.example.advprojectuts160421118.databinding.FragmentLoginBinding
import com.example.advprojectuts160421118.databinding.FragmentRegisBinding
import com.example.advprojectuts160421118.viewmodel.LoginViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RegisFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentRegisBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnRegister.setOnClickListener {
            try {
                val password = binding.txtRegisPassword.text.toString()
                val username = binding.txtRegisUsername.text.toString()
                val namaDepan = binding.txtRegisFirstName.text.toString()
                val namaBelakang = binding.txtRegisLastName.text.toString()
                val email = binding.txtRegisEmail.text.toString()
                val confPass = binding.txtRegisConfirmPassword.text.toString()

                if (password == confPass) {
                    viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
                    viewModel.regisUser(username,password,namaDepan, namaBelakang, email)
                }else{
                    Toast.makeText(requireContext(), "Masukan password yang benar", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}