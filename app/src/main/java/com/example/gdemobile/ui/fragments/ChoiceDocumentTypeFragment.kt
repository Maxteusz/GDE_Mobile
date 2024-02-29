package com.example.gdemobile.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentChoiceDocumentTypeBinding
import com.example.gdemobile.helpers.documenttypes.AcceptanceDocument
import com.example.gdemobile.helpers.documenttypes.IssuanceDocument
import com.example.gdemobile.ui.viewmodels.SharedViewModel


class ChoiceDocumentTypeFragment : Fragment() {

    private lateinit var binding : FragmentChoiceDocumentTypeBinding
    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChoiceDocumentTypeBinding.inflate(layoutInflater)
        initViews()

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    fun initViews()
    {
        binding.insideButton.setOnClickListener {
            if(sharedViewModel.documentType is AcceptanceDocument)
            sharedViewModel.documentType.subType = AcceptanceDocument.Internal()
            if (sharedViewModel.documentType is IssuanceDocument)
                sharedViewModel.documentType.subType = IssuanceDocument.Internal()
            findNavController().navigate(R.id.action_choiceDocumentTypeFragment_to_documentListFragment)
        }
        binding.outsideButton.setOnClickListener {
            if(sharedViewModel.documentType is AcceptanceDocument)
                sharedViewModel.documentType.subType = AcceptanceDocument.External()
            if (sharedViewModel.documentType is IssuanceDocument)
                sharedViewModel.documentType.subType = IssuanceDocument.External()
            findNavController().navigate(R.id.action_choiceDocumentTypeFragment_to_documentListFragment)
        }
    }


}