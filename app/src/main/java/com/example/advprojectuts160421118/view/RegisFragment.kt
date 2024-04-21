package com.example.advprojectuts160421118.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advprojectuts160421118.databinding.FragmentRegisBinding
import com.example.advprojectuts160421118.viewmodel.UserVisewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RegisFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisFragment : Fragment() {
    private lateinit var viewModel: UserVisewModel
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
        viewModel = ViewModelProvider(this).get(UserVisewModel::class.java)
        viewModel.regisLD.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                Toast.makeText(requireContext(), "Registrasi berhasil", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Registrasi gagal", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnRegister.setOnClickListener {
            try {
                val password = binding.txtRegisPassword.text.toString()
                val username = binding.txtRegisUsername.text.toString()
                val namaDepan = binding.txtRegisFirstName.text.toString()
                val namaBelakang = binding.txtRegisLastName.text.toString()
                val email = binding.txtRegisEmail.text.toString()
                val confPass = binding.txtRegisConfirmPassword.text.toString()

                if (password == confPass) {
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