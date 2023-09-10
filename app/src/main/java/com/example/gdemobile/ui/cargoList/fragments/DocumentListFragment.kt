package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentListBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.ContractorAdapter
import com.example.gdemobile.ui.cargoList.adapters.DocumentsAdapter
import kotlinx.coroutines.launch


class DocumentListFragment : Fragment() {

    private lateinit var  binding : FragmentDocumentListBinding
    private lateinit var viewModel: InssuingCargoListViewModel
    private lateinit var recyclerViewAdapter : DocumentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewLifecycleOwner.lifecycleScope.launch {
            whenStarted {
                if(viewModel.documentListInTemp.value?.isEmpty() == true)
                 viewModel.getDocumentsInTemp()
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.documentListInTemp.observe(viewLifecycleOwner, Observer {
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                //recyclerViewAdapter = DocumentsAdapter()
                //binding.recyclerview.adapter = recyclerViewAdapter
                //(it.layoutManager as LinearLayoutManager).scrollToPosition(binding.recyclerview.size)
            }
        })
    }
}