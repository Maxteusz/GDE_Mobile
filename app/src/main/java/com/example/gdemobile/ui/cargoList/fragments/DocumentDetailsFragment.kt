package com.example.gdemobile.ui.cargoList.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.ReceivingCargoListViewModel
import com.example.gdemobile.utils.DateFormat
import com.example.gdemobile.utils.ExtensionFunction.Companion.getDate
import java.time.format.DateTimeFormatter


class DocumentDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDocumentDetailsBinding
    private lateinit var viewModel: InssuingCargoListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        binding = FragmentDocumentDetailsBinding.inflate(layoutInflater)
        binding.document = viewModel.document.value

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
            val dataPicker = DatePickerDialog(requireContext())
            dataPicker.show()
            dataPicker.setOnDateSetListener({ view, year, month, dayOfMonth ->

                //viewModel.document.value?.date = dataPicker.getDate()
                binding.dateEdittext.setText(dataPicker.getDate().format(DateTimeFormatter.ofPattern(DateFormat.SIMPLE_DATE)))


            })


        }







        return binding.root
    }


}