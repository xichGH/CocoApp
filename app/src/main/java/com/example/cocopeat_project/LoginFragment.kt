package com.example.cocopeat_project

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cocopeat_project.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = AppDatabase.getInstance(application).userDao

        val viewModelFactory = LoginViewModelFactory(dataSource, application)

        val loginViewModel =
            ViewModelProvider(
                this, viewModelFactory)[LoginViewModel::class.java]

        binding.textSignUpNow.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        loginViewModel.navigateToHome.observe(viewLifecycleOwner, Observer {
            if (!isPasswordValid(binding.passwordEditText.text!!)) {
                binding.loginPassword.error = "AAAA"
            } else {
                // Clear the error.
                binding.loginPassword.error = null
            }
            this.findNavController().navigate(
                R.id.action_loginFragment_to_homeFragment)
            loginViewModel.doneNavigating()
        })

        binding.loginViewModel= loginViewModel

        binding.setLifecycleOwner(this)
        return binding.root
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}
