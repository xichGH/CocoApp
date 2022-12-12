package com.example.cocopeat_project

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cocopeat_project.databinding.FragmentLoginBinding
import com.example.cocopeat_project.AppDatabase
import com.example.cocopeat_project.RegistrationRepository

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dao = AppDatabase.getInstance(application).userDao

        val repository = RegistrationRepository(dao)

        val factory = LoginViewModelFactory(repository, application)


        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.loginViewModel = loginViewModel

        binding.lifecycleOwner = this

//        binding.textSignUpNow.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
//        }

        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                displayUsersList()
                loginViewModel.doneNavigatingRegiter()
            }
        })

        loginViewModel.navigatetoHome.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                navigateHome()
                loginViewModel.doneNavigatingLogin()
            }
        })

        loginViewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.donetoast()
            }
        })

        loginViewModel.errotoastUsername.observe(viewLifecycleOwner, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(
                    requireContext(),
                    "User doesnt exist,please Register!",
                    Toast.LENGTH_SHORT
                ).show()
                loginViewModel.donetoastErrorUsername()
            }
        })

        loginViewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please check your Password", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.donetoastInvalidPassword()
            }
        })


        return binding.root
    }

//    private fun isPasswordValid(text: Editable?): Boolean {
//        return text != null && text.length >= 8
//    }
    private fun displayUsersList() {
//        Log.i("MYTAG","insidisplayUsersList")
//        val action = LoginDirections.actionLoginToRegistration()
//        NavHostFragment.findNavController(this).navigate(action)
        view?.findNavController()?.navigate(R.id.action_loginFragment_to_registerFragment)

    }

    private fun navigateHome() {
        Log.i("MYTAG", "insidisplayUsersList")
//        val action = LoginFragmentDirections.actionLoginFragmentToUserDetailsFragment()
//        NavHostFragment.findNavController(this).navigate(action)
        view?.findNavController()?.navigate(R.id.action_loginFragment_to_homeFragment)
    }
}
