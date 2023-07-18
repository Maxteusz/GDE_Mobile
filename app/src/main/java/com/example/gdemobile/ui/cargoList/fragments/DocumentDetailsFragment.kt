package com.example.gdemobile.ui.cargoList.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.ui.cargoList.CargoListViewModel
import com.example.gdemobile.utils.ExtensionFunction
import java.util.Date


class DocumentDetailsFragment : Fragment() {

private lateinit var binding : FragmentDocumentDetailsBinding
    private lateinit var viewModel: CargoListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(CargoListViewModel::class.java)
        binding.document = viewModel.document.value


        binding = FragmentDocumentDetailsBinding.inflate(layoutInflater)
        binding.contractorTextfield.setEndIconOnClickListener({
            findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment)
        })
        binding.contractorEdittext.setOnClickListener {
            findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment)
        }
        binding.dokdefTextfield.setEndIconOnClickListener({
            findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment)
        })
        binding.dokdefEdittext.setOnClickListener {
            findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment)
        }
        binding.dateEdittext.setOnClickListener {
            val dataPicekr = DatePickerDialog(requireContext())

        }





        return binding.root
    }




}