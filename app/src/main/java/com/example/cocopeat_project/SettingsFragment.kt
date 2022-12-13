package com.example.cocopeat_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.cocopeat_project.databinding.FragmentLoginBinding
import com.example.cocopeat_project.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater,
            R.layout.fragment_settings,container,false)

        binding.buttonSave.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_settingsFragment_to_ongoingTaskFragment)
        }

        binding.buttonCancel.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
        }
        return binding.root
    }
}
