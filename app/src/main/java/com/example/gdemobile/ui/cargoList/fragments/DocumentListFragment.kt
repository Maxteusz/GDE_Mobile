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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.databinding.ErrorLayoutBinding
import com.example.gdemobile.databinding.FragmentDocumentListBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentsAdapter
import kotlinx.coroutines.launch


class DocumentListFragment : Fragment(), StateResponse {

    private lateinit var binding: FragmentDocumentListBinding
    private lateinit var viewModel: InssuingCargoListViewModel
    private lateinit var recyclerViewAdapter: DocumentsAdapter
    private lateinit var errorLayoutBinding: ErrorLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentListBinding.inflate(layoutInflater)
        errorLayoutBinding = binding.errorlayout
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewModel.stateResponse = this
        viewLifecycleOwner.lifecycleScope.launch {
            whenStarted {
                if (viewModel.documentListInTemp.value?.isEmpty() == true)
                    viewModel.getDocumentsInTemp()
            }
        }
        return binding.root
    }

    val listener = object : DocumentsAdapter.CustomViewHolderListener {
        override fun onCustomItemClicked(document: Document) {

            val action =
                DocumentListFragmentDirections.actionDocumentListFragmentToCargoListFragment(document.id)
            findNavController().navigate(action)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.documentListInTemp.observe(viewLifecycleOwner, Observer {
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                recyclerViewAdapter =
                    DocumentsAdapter(viewModel.documentListInTemp.value!!, listener)
                binding.recyclerview.adapter = recyclerViewAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.recyclerview.size)
            }
        })
    }

    override fun OnLoading() {
        binding.loadinglayout.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
    }

    override fun OnError(message: String) {
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.GONE
        errorLayoutBinding.errorTextview.text = message
        binding.errorlayout.root.visibility = View.VISIBLE
    }

    override fun <T> OnSucces(result: T?) {
        binding.errorlayout.root.visibility = View.GONE
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
    }
}