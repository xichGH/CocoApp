package com.example.cocopeat_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.cocopeat_project.databinding.FragmentOngoingtaskBinding
import com.example.cocopeat_project.databinding.FragmentSettingsBinding

class OngoingTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentOngoingtaskBinding>(inflater,
            R.layout.fragment_ongoingtask,container,false)

        binding.addProcess.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_ongoingTaskFragment_to_settingsFragment)
        }

        binding.buttonDone.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_ongoingTaskFragment_to_completeFragment)
        }

        binding.buttonStop.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_ongoingTaskFragment_to_cancelFragment)
        }

        return binding.root
    }
}
