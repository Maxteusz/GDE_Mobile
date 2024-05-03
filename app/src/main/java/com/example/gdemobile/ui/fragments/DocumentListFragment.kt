package com.example.gdemobile.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentListBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.adapters.DocumentsAdapter
import com.example.gdemobile.ui.viewmodels.DocumentViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel


class DocumentListFragment : Fragment(), IStateResponse {

    private lateinit var binding: FragmentDocumentListBinding
    private lateinit var viewModel: DocumentViewModel
    private lateinit var recyclerViewAdapter: DocumentsAdapter

    private val sharedViewModel : SharedViewModel by activityViewModels()



    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDocumentListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[DocumentViewModel::class.java]
        viewModel.stateResponse = this
        initObservers()
        initComponentsMethod()
        setColorsProgressSwipeLayout()

        return binding.root
    }

    val listener = object : DocumentsAdapter.CustomViewHolderListener {
        override fun onCustomItemClicked(document: Document) {
           sharedViewModel.setDocument(document)
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
       sharedViewModel.actionType.observe(viewLifecycleOwner, Observer {
                viewModel.getDocuments(sharedViewModel.getActionType()!!)
                //initRecyclerViewAdapter(it)

        })
       viewModel.documents.observe(viewLifecycleOwner, Observer {
            binding.recyclerview.also {
                initRecyclerViewAdapter(it)
            }
        })
    }

    private fun initRecyclerViewAdapter(recyclerView : RecyclerView)
    {
        recyclerView.recycledViewPool.clear()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerViewAdapter =
            DocumentsAdapter(viewModel.documents.value!!, listener)
        binding.recyclerview.adapter = recyclerViewAdapter
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(binding.recyclerview.size)
    }
    private fun initComponentsMethod()
    {
        binding.newdocumentButton.setOnClickListener {
            sharedViewModel.setDocument(Document())
            findNavController().navigate(R.id.action_documentListFragment_to_documentDetailsFragment)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getDocuments(sharedViewModel.getActionType()!!)
        }

        binding.searchBar.searchTextfield.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.filterDocuments(s.toString())
            }

        })
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onResume() {
        super.onResume()

        //viewModel.getDocuments(sharedViewModel.getDocumentType()!!)
        binding.recyclerview
            .layoutManager?.onRestoreInstanceState(viewModel.recyclerViewScrollState)
    }
    override fun onPause() {
        super.onPause()
        viewModel.recyclerViewScrollState =
            binding.recyclerview.layoutManager?.onSaveInstanceState()

    }


    override fun onLoading() {
        binding.loadinglayout.root.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = false

    }

    override suspend fun onError(message: String) {
        binding.loadinglayout.root.visibility = View.GONE
        binding.succeslayout.visibility = View.GONE
        binding.errorlayout.errorTextview.text = message
        binding.swipeRefreshLayout.isRefreshing = false
        binding.errorlayout.root.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        binding.errorlayout.root.visibility = View.GONE
        binding.loadinglayout.root.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
    }


}