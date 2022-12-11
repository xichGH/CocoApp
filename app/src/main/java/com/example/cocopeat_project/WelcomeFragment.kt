package com.example.cocopeat_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.cocopeat_project.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater,
            R.layout.fragment_welcome,container,false)

        binding.buttonSignIn.setOnClickListener { view : View ->

            view.findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)

        }

        return binding.root
    }
}