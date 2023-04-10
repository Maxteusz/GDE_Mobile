package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.utils.Utils


class DocumentDetailsFragment : Fragment() {

private lateinit var binding : FragmentDocumentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentDetailsBinding.inflate(layoutInflater)
        binding.contractorTextfield.setEndIconOnClickListener({
            findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment)
        })
        binding.contractorEdittext.setOnClickListener {
            binding.contractorEdittext.setText("fdfdfd")
            findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment)
        }
        binding.contractorTextfield.setOnClickListener {
            it.alpha = 0.0F
            Utils.showToast(requireContext(),"Click")

        }
        return binding.root
    }


}