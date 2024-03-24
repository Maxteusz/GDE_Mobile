package com.example.gdemobile.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentMenuBinding
import com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes.Acceptance
import com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes.Issuance
import com.example.gdemobile.ui.viewmodels.SharedViewModel


class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding
    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater);
        initViews()
        return binding.root
    }

    fun initViews()
    {
        binding.confButton.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_configurationFragment) }
        binding.receivingButton.setOnClickListener {  sharedViewModel.setActionType(
            Acceptance()
        )
            findNavController().navigate(R.id.action_menuFragment_to_choiceDocumentTypeFragment) }
        binding.issuingButton.setOnClickListener {
           sharedViewModel.setActionType(Issuance())
            findNavController().navigate(R.id.action_menuFragment_to_choiceDocumentTypeFragment)
        }
    }


}