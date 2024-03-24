package com.example.gdemobile.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes.Acceptance
import com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes.Issuance
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.viewmodels.DocumentViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class DocumentDetailsFragment : Fragment(), IStateResponse {

    private lateinit var binding: FragmentDocumentDetailsBinding
    private lateinit var documentViewModel : DocumentViewModel
    private var createdDocument : Deferred<Document?>? = null

    private val sharedViewModel : SharedViewModel by activityViewModels()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        documentViewModel = ViewModelProvider(requireActivity())[DocumentViewModel::class.java]
        documentViewModel.stateResponse = this

        binding = FragmentDocumentDetailsBinding.inflate(inflater, container, false)

        sharedViewModel.document.observe(viewLifecycleOwner, Observer {
            binding.document = it
        })
        hideContractorField()


        //Section Document Definition
        binding.dokdefTextfield.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }
        binding.dokdefTextfield.setEndIconOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }
        binding.dokdefEdittext.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }

        //Section Contractor
        binding.contractorEdittext.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }
        binding.contractorTextfield.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }
        binding.contractorTextfield.setEndIconOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }

        //Section Warehouse
        binding.warehouseEdittext.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_warehouseListFragment) }
        binding.warehouseTextfield.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_warehouseListFragment) }
        binding.warehouseTextfield.setEndIconOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_warehouseListFragment) }



        binding.nextButton.setOnClickListener {
            createdDocument = viewLifecycleOwner.lifecycleScope.async {
                return@async documentViewModel.createDocument(sharedViewModel.document.value!!)
            }


        }

        return binding.root
    }

    private fun hideContractorField()
    {
        when (sharedViewModel.getActionType()?.subType) {
            is Acceptance.Internal -> {
                binding.contractorEdittext.visibility = View.GONE
                binding.contractorTextfield.visibility = View.GONE
            }
            is Issuance.Internal -> {
                binding.contractorEdittext.visibility = View.GONE
                binding.contractorTextfield.visibility = View.GONE
            }
        }
    }

    override fun OnLoading() {
        binding.loadinglayout.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE

    }

    override suspend fun OnError(message: String) {
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
        context?.let { CustomToast.showToast(it,message,CustomToast.Type.Error) }
    }

    override fun OnSucces() {
        binding.loadinglayout.visibility = View.GONE
        viewLifecycleOwner.lifecycleScope.launch {
            createdDocument?.await()?.let { sharedViewModel.setDocument(it) }
            sharedViewModel.setBlockLoadData(true)
            findNavController().navigate(R.id.action_documentDetailsFragment_to_cargoListFragment)
        }


    }






    }


