package com.example.gdemobile.ui.cargoList.fragments

import android.annotation.SuppressLint
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
import com.example.gdemobile.databinding.FragmentContractorListBinding
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.CargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.ContractorAdapter
import kotlinx.coroutines.launch

class ContractorListFragment : Fragment(), StateResponse {

    private lateinit var binding: FragmentContractorListBinding
    private lateinit var viewModel: CargoListViewModel
    private lateinit var contractorAdapter : ContractorAdapter
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContractorListBinding.inflate(layoutInflater);
        viewModel = ViewModelProvider(requireActivity()).get(CargoListViewModel::class.java)
        viewModel.stateResponse = this
        viewLifecycleOwner.lifecycleScope.launch {
            whenStarted {
                if(viewModel.contractors.value?.isEmpty() == true)
                viewModel.getContractors()
            }
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contractors.observe(viewLifecycleOwner, Observer {
            binding.contractorsRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                contractorAdapter = ContractorAdapter(viewModel.contractors.value!!.sortedBy { n -> n.name }, viewModel, this)
                binding.contractorsRecyclerview.adapter = contractorAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.contractorsRecyclerview.size)
            }
        })
    }
    override fun OnLoading() {
       binding.cicularIcon.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
    }
    override fun OnError() {
        //binding.loadinglayout.visibility = View.GONE
    }
    override fun OnSucces() {
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
    }


}