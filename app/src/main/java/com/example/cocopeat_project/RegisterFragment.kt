package com.example.cocopeat_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.cocopeat_project.databinding.FragmentLoginBinding
import com.example.cocopeat_project.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,
            R.layout.fragment_register,container,false)

        binding.textSignInNow2.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.buttonSignUp.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return binding.root
    }
}