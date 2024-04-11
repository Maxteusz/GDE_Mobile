package com.example.gdemobile.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.databinding.FragmentWarehouseResourceDetailBinding
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.adapters.WarehouseResourcesAdapter
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.ui.viewmodels.WarehouseResourceViewModel
import com.example.gdemobile.utils.CustomToast

class WarehouseResourceDetailFragment : Fragment(), IStateResponse {

    private lateinit var _binding: FragmentWarehouseResourceDetailBinding
    private lateinit var viewModel: WarehouseResourceViewModel
    private val _sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var _recyclerViewAdapter: WarehouseResourcesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWarehouseResourceDetailBinding.inflate(layoutInflater)
        _binding.warehouseResource = _sharedViewModel.getWarehouseResource()
        viewModel = ViewModelProvider(requireActivity())[WarehouseResourceViewModel::class.java]
        viewModel.stateResponse = this
        _initObservers()
            viewModel.getExtendedWarehouseResourceInformation(
                _sharedViewModel.getWarehouseResource()?.cargo?.id!!,
                _sharedViewModel.getWarehouseResource()?.warehouse?.ID!!
            )

        return _binding.root
    }

    private fun _initObservers() {
        viewModel.extendedWarehouseResources.observe(viewLifecycleOwner)
        { list ->
            _binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                _recyclerViewAdapter = WarehouseResourcesAdapter(
                    list.sortedByDescending { a -> a.document.number },
                    WarehouseResourcesAdapter.WarehouseResourceType.EXTENSION
                )
                _binding.recyclerview.adapter = _recyclerViewAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(_binding.recyclerview.size)

            }
        }
    }


    override fun onLoading() {
        _binding.loadinglayout.root.visibility = View.VISIBLE
        _binding.succeslayout.visibility = View.GONE

    }

    override suspend fun onError(message: String) {
        _binding.loadinglayout.root.visibility = View.GONE
        _binding.succeslayout.visibility = View.GONE
        context?.let { CustomToast.showToast(it,message, CustomToast.Type.Error) }
    }

    override fun onSuccess() {
        _binding.loadinglayout.root.visibility = View.GONE
        _binding.succeslayout.visibility = View.VISIBLE
    }


}