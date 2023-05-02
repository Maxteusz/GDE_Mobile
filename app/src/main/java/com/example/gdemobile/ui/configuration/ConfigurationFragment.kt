package com.example.gdemobile.ui.configuration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdemobile.R
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
        return binding.root
    }



}