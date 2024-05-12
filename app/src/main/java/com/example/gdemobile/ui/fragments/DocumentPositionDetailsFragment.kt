package com.example.gdemobile.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.databinding.FragmentDocumentPositionDetailsBinding
import com.example.gdemobile.models.Currency
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.models.WarehouseResource
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.adapters.ListViewAdapter
import com.example.gdemobile.ui.adapters.WarehouseResourcesAdapter
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.ui.viewmodels.WarehouseResourceViewModel
import com.example.gdemobile.utils.CustomToast
import kotlinx.coroutines.launch


class DocumentPositionDetailsFragment : Fragment(), IStateResponse {

    private lateinit var binding: FragmentDocumentPositionDetailsBinding
    private lateinit var _documentPosition: DocumentPosition
    private lateinit var _warehouseResourceViewModel: WarehouseResourceViewModel
    private lateinit var _warehouseResourceAdapter: WarehouseResourcesAdapter
    val sharedViewModel: SharedViewModel by activityViewModels()


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDocumentPositionDetailsBinding.inflate(layoutInflater)
        _documentPosition = sharedViewModel.documentPosition.value!!

        _warehouseResourceViewModel =
            ViewModelProvider(requireActivity())[WarehouseResourceViewModel::class.java]
        _warehouseResourceViewModel.stateResponse = this
          initAdapters()
       initObservers()
        blockWidget()
        binding.documentPosition = _documentPosition
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.setBlockLoadData(true)
        lifecycleScope.launch {
            sharedViewModel.documentPosition.value?.cargo?.let {
                _warehouseResourceViewModel
                    .getPrimaryWarehouseResourceInformation(it.id)
            }
        }
    }



    private fun initAdapters() {
        val currencyAdapter = ListViewAdapter(
            requireActivity(),
            Currency.symbols as MutableList<String>
        )

        binding.currencysymbolSpinner.setAdapter(currencyAdapter)
        binding.currencysymbolSpinner.threshold = 100000

        val unitAdapter = ListViewAdapter(
            requireActivity(),
            _documentPosition.cargo?.additionalUnits!! as MutableList<String>
        )
        binding.unitSpinner.threshold = 10000
        binding.unitSpinner.setAdapter(unitAdapter)
    }

    private val onClickListener = object : WarehouseResourcesAdapter.IOnClickListener {
        override fun onClick(warehouseResource: WarehouseResource) {
            sharedViewModel.setWarehouseResource(warehouseResource)
            findNavController().navigate(com.example.gdemobile.R.id.action_documentPositionDetailsFragment_to_warehouseResourceDetailFragment)

        }
    }

    private fun initObservers() {
        _warehouseResourceViewModel.primaryWarehouseResources.observe(viewLifecycleOwner) { list ->
            binding.warehousesRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                _warehouseResourceAdapter = WarehouseResourcesAdapter(
                    list,
                    WarehouseResourcesAdapter.WarehouseResourceType.PRIMARY,
                    onClickListener
                )
                it.adapter = _warehouseResourceAdapter
            }
        }
    }

    fun blockWidget() {
        binding.unitSpinner.isEnabled = !_documentPosition.cargo?.additionalUnits.isNullOrEmpty()


    }

    override fun onLoading() {
      binding.loadinglayout.root.visibility = View.VISIBLE
    }

    override suspend fun onError(message: String) {
        context?.let { CustomToast.showToast(it, message, CustomToast.Type.Error) }
    }

    override fun onSuccess() {
        binding.loadinglayout.root.visibility = View.GONE
    }
}
