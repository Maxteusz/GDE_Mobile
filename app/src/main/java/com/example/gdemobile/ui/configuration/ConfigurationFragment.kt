package com.example.gdemobile.ui.configuration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentConfigurationBinding


class ConfigurationFragment : Fragment() {

lateinit var binding : FragmentConfigurationBinding
lateinit var viewModel: ConfigurationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfigurationBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity()).get(ConfigurationViewModel::class.java)
        viewModel.binding = binding
        binding.saveButton.setOnClickListener {
            viewModel.setConfiguration()
            Config.saveConfiguration(requireActivity())
            requireActivity().finish()
        }
        return binding.root
    }




}