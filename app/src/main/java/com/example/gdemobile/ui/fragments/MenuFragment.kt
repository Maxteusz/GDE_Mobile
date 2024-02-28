package com.example.gdemobile.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater);
        initViews()
        return binding.root
    }

    fun initViews()
    {
        binding.confButton.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_configurationFragment) }
        binding.receivingButton.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_documentListFragment) }
    }


}