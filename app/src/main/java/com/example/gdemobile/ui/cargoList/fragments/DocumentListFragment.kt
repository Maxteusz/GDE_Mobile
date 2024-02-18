package com.example.gdemobile.ui.cargoList.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentListBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.adapters.DocumentsAdapter
import com.example.gdemobile.ui.viewmodels.DocumentListViewModel


class DocumentListFragment : Fragment(), IStateResponse {

    private lateinit var binding: FragmentDocumentListBinding
    private lateinit var viewModel: DocumentListViewModel
    private lateinit var recyclerViewAdapter: DocumentsAdapter



    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[DocumentListViewModel::class.java]
        viewModel.stateResponse = this
        initObservers()
        initComponentsMethod()
        setColorsProgressSwipeLayout()
        return binding.root
    }

    val listener = object : DocumentsAdapter.CustomViewHolderListener {
        override fun onCustomItemClicked(x: Document?) {
            findNavController().navigate(
                R.id.action_documentListFragment_to_cargoListFragment
            )
        }

    }



    @SuppressLint("ResourceType")
    fun setColorsProgressSwipeLayout() {
        binding.swipeRefreshLayout.setColorSchemeColors(resources.getInteger(R.color.orange))
        binding.swipeRefreshLayout.setProgressBackgroundColorSchemeColor(resources.getInteger(R.color.darkGray))
    }

    fun initObservers() {
        viewModel.documents.observe(viewLifecycleOwner, Observer {
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                if (viewModel.documents.value != null) {
                    recyclerViewAdapter =
                        DocumentsAdapter(viewModel.documents.value!!, listener)
                }
                binding.recyclerview.adapter = recyclerViewAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.recyclerview.size)
            }
        })
    }
    fun initComponentsMethod()
    {
        binding.newdocumentButton.setOnClickListener {
            findNavController().navigate(R.id.action_documentListFragment_to_documentDetailsFragment)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getDocuments()
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.getDocuments()
    }


    override fun OnLoading() {
        binding.loadinglayout.root.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override suspend fun OnError(message: String) {
        binding.loadinglayout.root.visibility = View.GONE
        binding.succeslayout.visibility = View.GONE
        binding.errorlayout.errorTextview.text = message
        binding.swipeRefreshLayout.isRefreshing = false
        binding.errorlayout.root.visibility = View.VISIBLE
    }

    override fun OnSucces() {
        binding.errorlayout.root.visibility = View.GONE
        binding.loadinglayout.root.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
    }
}