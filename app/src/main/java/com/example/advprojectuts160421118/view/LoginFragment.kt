package com.example.advprojectuts160421118.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advprojectuts160421118.R
import com.example.advprojectuts160421118.databinding.FragmentHobbyListBinding
import com.example.advprojectuts160421118.databinding.FragmentLoginBinding
import com.example.advprojectuts160421118.viewmodel.ListViewModelHobby
import com.example.advprojectuts160421118.viewmodel.LoginViewModel
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            if (user != null && user.isNotEmpty()) {
                // Jika user tidak null dan tidak kosong, navigasikan ke layar Hobby List
                val action = LoginFragmentDirections.actionLoginFragmentToHobbyListFragment()
                Navigation.findNavController(view).navigate(action)
            }
        })
        binding.btnLogin.setOnClickListener {
            try {
                val username = binding.txtLoginUsername.text.toString()
                val password = binding.txtLoginPassword.text.toString()

                if (username.isNotEmpty() && password.isNotEmpty()) {

                    viewModel.loginUser(username, password)
                }else{
                    Toast.makeText(requireContext(), "Masukkan Username dan Password", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        binding.txtRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLregisFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearUser() // Membersihkan data user saat Fragment dihancurkan
    }

}