package com.example.gdemobile.ui.fragments

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
import com.example.gdemobile.ui.adapters.ContractorAdapter
import com.example.gdemobile.ui.viewmodels.ContractorViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast

class ContractorListFragment : Fragment(), IStateResponse {

    private lateinit var _binding: FragmentContractorListBinding
    private lateinit var _contractorAdapter : ContractorAdapter
    private lateinit var _contractorViewModel : ContractorViewModel
    private val _sharedViewModel : SharedViewModel by activityViewModels()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContractorListBinding.inflate(layoutInflater);
        _contractorViewModel = ViewModelProvider(requireActivity()).get(ContractorViewModel::class.java)
        _contractorViewModel.stateResponse = this
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _contractorViewModel.getContractors()
    }

    val listener = object : ContractorAdapter.ViewHolderListener {
        override fun onItemClicked(contractor: Contractor) {
            _sharedViewModel.document.value?.contractor = contractor
            findNavController().popBackStack()

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _contractorViewModel.contractors.observe(viewLifecycleOwner, Observer {
            _binding.contractorsRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                _contractorAdapter = ContractorAdapter(_contractorViewModel.contractors.value!!.sortedBy { n -> n.name }, listener)
                _binding.contractorsRecyclerview.adapter = _contractorAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(_binding.contractorsRecyclerview.size)
            }
        })
    }
    override fun OnLoading() {
       _binding.loadinglayout.root.visibility = View.VISIBLE
        _binding.succeslayout.visibility = View.GONE
    }
    override suspend fun OnError(message: String) {
        _binding.loadinglayout.root.visibility = View.INVISIBLE
        context?.let { CustomToast.showToast(it,message,CustomToast.Type.Error) }
    }
    override fun  OnSucces() {
        _binding.loadinglayout.root.visibility = View.GONE
        _binding.succeslayout.visibility = View.VISIBLE
    }


}