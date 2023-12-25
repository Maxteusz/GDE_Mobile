package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ErrorLayoutBinding
import com.example.gdemobile.databinding.FragmentDocumentListBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentsAdapter
import com.example.gdemobile.utils.NamesSharedVariable
import kotlinx.coroutines.launch


class DocumentListFragment : Fragment(), IStateResponse {

    private lateinit var binding: FragmentDocumentListBinding
    private lateinit var viewModel: InssuingCargoListViewModel
    private lateinit var recyclerViewAdapter: DocumentsAdapter
    private lateinit var errorLayoutBinding: ErrorLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentListBinding.inflate(layoutInflater)
        errorLayoutBinding = binding.errorlayout
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewModel.stateResponse = this
        binding.newdocumentButton.setOnClickListener {
            findNavController().navigate(R.id.action_documentListFragment_to_documentDetailsFragment)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            if(viewModel.documentListInTemp.value.isNullOrEmpty())
            viewModel.getDocumentsInTemp()

        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getDocumentsInTemp()
            }
        }
        return binding.root
    }

    val listener = object : DocumentsAdapter.CustomViewHolderListener {
        override fun onCustomItemClicked(document: Document) {
            viewModel.document.value = document
            viewModel.isRequiredLoadData.value = true

            findNavController().navigate(
                R.id.action_documentListFragment_to_cargoListFragment)}

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.recyclerViewScrollState.observe(viewLifecycleOwner, Observer { state ->
            binding.recyclerview.layoutManager?.onRestoreInstanceState(state)
        })
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

    override fun onResume() {
        super.onResume()
        binding.recyclerview.layoutManager?.onRestoreInstanceState(viewModel.recyclerViewScrollState.value)
    }

    override fun onPause() {
        super.onPause()
        viewModel.recyclerViewScrollState.value = binding.recyclerview.layoutManager?.onSaveInstanceState()

    }

    override fun OnLoading() {
        binding.loadinglayout.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override suspend fun OnError(message: String) {
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.GONE
        errorLayoutBinding.errorTextview.text = message
        binding.swipeRefreshLayout.isRefreshing = false
        binding.errorlayout.root.visibility = View.VISIBLE
    }

    override fun OnSucces() {
        binding.errorlayout.root.visibility = View.GONE
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
    }
}