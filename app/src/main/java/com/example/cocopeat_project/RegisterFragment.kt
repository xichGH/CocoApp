package com.example.cocopeat_project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cocopeat_project.R
import com.example.cocopeat_project.UserDao
import com.example.cocopeat_project.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = AppDatabase.getInstance(application).userDao

        val viewModelFactory = RegisterViewModelFactory(dataSource, application)

        val registerViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(RegisterViewModel::class.java)

        registerViewModel.navigateToLogin.observe(this, Observer {
            this.findNavController().navigate(
                R.id.action_registerFragment_to_loginFragment
            )
            registerViewModel.doneNavigating()
        })

        binding.registerViewModel = registerViewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }
}