package com.example.gdemobile.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast


class DocumentDetailsFragment : Fragment(), IStateResponse {

    private lateinit var binding: FragmentDocumentDetailsBinding

    private val sharedViewModel : SharedViewModel by activityViewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDocumentDetailsBinding.inflate(inflater, container, false)

        //Section Document Definition
        binding.dokdefTextfield.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }
        binding.dokdefTextfield.setEndIconOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }
        binding.dokdefEdittext.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }

        //Section Contractor
        binding.contractorEdittext.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }
        binding.contractorTextfield.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }
        binding.contractorTextfield.setEndIconOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }

        sharedViewModel.document.observe(viewLifecycleOwner, Observer
        {
            binding.document = it
        })
        return binding.root
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


    }


}