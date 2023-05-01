package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.ui.cargoList.CargoListView
import com.example.gdemobile.utils.Utils


class DocumentDetailsFragment : Fragment() {

private lateinit var binding : FragmentDocumentDetailsBinding
    private lateinit var viewModel: CargoListView
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
            findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment)
        }
        viewModel = ViewModelProvider(requireActivity()).get(CargoListView::class.java)
        viewModel.document.observe(viewLifecycleOwner, {doc ->
            binding.contractorEdittext.setText(doc.contractor.Code)
        })


        return binding.root
    }


}