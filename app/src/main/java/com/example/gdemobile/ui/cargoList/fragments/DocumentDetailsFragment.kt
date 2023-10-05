package com.example.gdemobile.ui.cargoList.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.BaseServiceCargoViewModel
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentDefinitionAdapter
import com.example.gdemobile.ui.cargoList.adapters.DocumentsAdapter
import com.example.gdemobile.utils.DateFormat
import com.example.gdemobile.utils.ExtensionFunction.Companion.fromJson
import com.example.gdemobile.utils.ExtensionFunction.Companion.getDate
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import java.time.format.DateTimeFormatter


class DocumentDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDocumentDetailsBinding
    private lateinit var viewModel: BaseServiceCargoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
            viewModel.document.value = Document()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDocumentDetailsBinding.inflate(layoutInflater)

        val document = viewModel.document.value

        viewModel.document.observe(viewLifecycleOwner, Observer
        {

            binding.document = document
        })



        //Section Document Definition
        binding.dokdefTextfield.setOnClickListener {
            findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment)
        }
        binding.dokdefTextfield.setEndIconOnClickListener {
            findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment)
        }
        binding.dokdefEdittext.setOnClickListener {
            findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment)
        }



        return binding.root
    }




}