package com.example.cocopeat_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.cocopeat_project.databinding.FragmentCancelBinding

class CancelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentCancelBinding>(inflater,
            R.layout.fragment_cancel,container,false)

        binding.addProcess.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_cancelFragment_to_settingsFragment)
        }

        return binding.root
    }
}
