package com.example.gdemobile.ui.fragments

import android.os.Bundle
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
import com.example.gdemobile.databinding.FragmentWarehouseListBinding
import com.example.gdemobile.models.Warehouse
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.adapters.WarehouseAdapter
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.ui.viewmodels.WarehouseViewModel
import com.example.gdemobile.utils.CustomToast


class WarehouseListFragment : Fragment(), IStateResponse{
    private lateinit var  _binding : FragmentWarehouseListBinding
    private lateinit var _warehouseViewModel : WarehouseViewModel
    private lateinit var _warehouseAdapter : WarehouseAdapter
    private val _sharedViewModel : SharedViewModel by activityViewModels()


    val clickListener = object  : WarehouseAdapter.ViewHolderListener {
        override fun onItemClicked(warehouse: Warehouse) {
            _sharedViewModel.document.value?.warehouse = warehouse
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWarehouseListBinding.inflate(layoutInflater);
        _warehouseViewModel= ViewModelProvider(requireActivity()).get(WarehouseViewModel::class.java)
        _warehouseViewModel.stateResponse = this
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _warehouseViewModel.warehouses.observe(viewLifecycleOwner, Observer {
            _binding.warehousesRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                _warehouseAdapter = WarehouseAdapter(_warehouseViewModel.warehouses.value!!.sortedBy { n -> n.name }, clickListener)
                _binding.warehousesRecyclerview.adapter = _warehouseAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(_binding.warehousesRecyclerview.size)
            }
        })
    }


    override fun onResume() {
        super.onResume()
        _warehouseViewModel.getWarehouses()
    }
    override fun OnLoading() {
        _binding.loadinglayout.root.visibility = View.VISIBLE
        _binding.succeslayout.visibility = View.GONE

    }
    override suspend fun OnError(message: String) {
        _binding.loadinglayout.root.visibility = View.INVISIBLE
        context?.let { CustomToast.showToast(it,message, CustomToast.Type.Error) }
    }
    override fun  OnSucces() {
        _binding.loadinglayout.root.visibility = View.GONE
        _binding.succeslayout.visibility = View.VISIBLE
    }
}



