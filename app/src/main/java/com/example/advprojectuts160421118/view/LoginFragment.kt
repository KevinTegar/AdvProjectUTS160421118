package com.example.advprojectuts160421118.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.advprojectuts160421118.databinding.FragmentLoginBinding
import com.example.advprojectuts160421118.viewmodel.UserVisewModel

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var viewModel: UserVisewModel
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

        viewModel = ViewModelProvider(this).get(UserVisewModel::class.java)
        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            if (user != null && user.isNotEmpty()) {

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
        viewModel.clearUser()
    }

}