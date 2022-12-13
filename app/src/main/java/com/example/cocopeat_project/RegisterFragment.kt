package com.example.cocopeat_project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cocopeat_project.R
import com.example.cocopeat_project.UserDao
import com.example.cocopeat_project.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        binding.textSignInNow2.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        val application = requireNotNull(this.activity).application

        val dao = AppDatabase.getInstance(application).userDao

        val repository = RegistrationRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)

        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)

        binding.registerViewModel = registerViewModel

        binding.lifecycleOwner = this

        registerViewModel.navigateto.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        })

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("MYTAG",it.toString()+"000000000000000000000000")
        })


        registerViewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoast()
            }
        })

        registerViewModel.errotoastUsername.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoastUserName()
            }
        })

        return binding.root
    }
    private fun displayUsersList() {
//        Log.i("MYTAG","insidisplayUsersList")
//        val action = RegistrationDirections.actionRegistrationToLogin()
//        NavHostFragment.findNavController(this).navigate(action)
        view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment)

    }
}