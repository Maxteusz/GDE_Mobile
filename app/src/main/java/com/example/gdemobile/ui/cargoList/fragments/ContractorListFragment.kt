package com.example.gdemobile.ui.cargoList.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.databinding.FragmentContractorListBinding
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.adapters.ContractorAdapter
import com.example.gdemobile.utils.CustomToast
import kotlinx.coroutines.launch

class ContractorListFragment : Fragment(), IStateResponse {

    private lateinit var binding: FragmentContractorListBinding
    private lateinit var contractorAdapter : ContractorAdapter
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContractorListBinding.inflate(layoutInflater);
        viewLifecycleOwner.lifecycleScope.launch {


        }
        return binding.root
    }

    val listener = object : ContractorAdapter.ViewHolderListener {
        override fun onItemClicked(contractor: Contractor) {
            findNavController().popBackStack()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*viewModel.contractors.observe(viewLifecycleOwner, Observer {
            binding.contractorsRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                contractorAdapter = ContractorAdapter(viewModel.contractors.value!!.sortedBy { n -> n.name }, listener)
                binding.contractorsRecyclerview.adapter = contractorAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.contractorsRecyclerview.size)
            }
        })*/
    }
    override fun OnLoading() {
       binding.cicularIcon.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
    }
    override suspend fun OnError(message: String) {
        context?.let { CustomToast.showToast(it,message,CustomToast.Type.Error) }
    }
    override fun  OnSucces() {
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
    }


}