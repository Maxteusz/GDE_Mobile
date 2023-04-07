package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentCargoListBinding
import com.example.gdemobile.databinding.FragmentContractorListBinding

class ContractorListFragment : Fragment() {

private lateinit var  binding : FragmentContractorListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContractorListBinding.inflate(layoutInflater);
        // Inflate the layout for this fragment
        return binding.root
    }



}