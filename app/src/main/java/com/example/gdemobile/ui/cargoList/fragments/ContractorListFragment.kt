package com.example.gdemobile.ui.cargoList.fragments

import android.annotation.SuppressLint
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
import com.example.gdemobile.databinding.FragmentContractorListBinding
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.adapters.ContractorAdapter
import com.example.gdemobile.ui.viewmodels.ContractorViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast

class ContractorListFragment : Fragment(), IStateResponse {

    private lateinit var binding: FragmentContractorListBinding
    private lateinit var contractorAdapter : ContractorAdapter
    private lateinit var contractorViewModel : ContractorViewModel
    private val sharedViewModel : SharedViewModel by activityViewModels()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContractorListBinding.inflate(layoutInflater);
        contractorViewModel = ViewModelProvider(requireActivity()).get(ContractorViewModel::class.java)
        contractorViewModel.stateResponse = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        contractorViewModel.getContractors()
    }

    val listener = object : ContractorAdapter.ViewHolderListener {
        override fun onItemClicked(contractor: Contractor) {
            sharedViewModel.document.value?.contractor = contractor
            findNavController().popBackStack()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contractorViewModel.contractors.observe(viewLifecycleOwner, Observer {
            binding.contractorsRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                contractorAdapter = ContractorAdapter(contractorViewModel.contractors.value!!.sortedBy { n -> n.name }, listener)
                binding.contractorsRecyclerview.adapter = contractorAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.contractorsRecyclerview.size)
            }
        })
    }
    override fun OnLoading() {
       binding.loadinglayout.root.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
    }
    override suspend fun OnError(message: String) {
        binding.loadinglayout.root.visibility = View.INVISIBLE
        context?.let { CustomToast.showToast(it,message,CustomToast.Type.Error) }
    }
    override fun  OnSucces() {
        binding.loadinglayout.root.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
    }


}