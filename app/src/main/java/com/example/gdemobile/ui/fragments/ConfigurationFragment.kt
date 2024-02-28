package com.example.gdemobile.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentConfigurationBinding


class ConfigurationFragment : Fragment() {

lateinit var binding : FragmentConfigurationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfigurationBinding.inflate(layoutInflater)
        binding.saveButton.setOnClickListener {
            setConfiguration()
            Config.saveConfiguration(requireActivity())
            findNavController().popBackStack()

        }
        return binding.root
    }
    fun setConfiguration()
    {
        Config.ip = binding.ipTextedit.text.toString()
        Config.port = binding.portTextedit.text.toString()
        Config.usernameERP = binding.usernameTextedit.text.toString()
        Config.passwordERP = binding.passwordTextedit.text.toString()
        Config.fastAddingDocumentPosition = binding.fastAddingDocumentposition.isChecked


    }



}