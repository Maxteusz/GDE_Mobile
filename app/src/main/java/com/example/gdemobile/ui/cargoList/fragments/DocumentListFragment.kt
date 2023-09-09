package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentListBinding
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel


class DocumentListFragment : Fragment() {

    private lateinit var  binding : FragmentDocumentListBinding
    private lateinit var viewModel: InssuingCargoListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        return binding.root
    }



}