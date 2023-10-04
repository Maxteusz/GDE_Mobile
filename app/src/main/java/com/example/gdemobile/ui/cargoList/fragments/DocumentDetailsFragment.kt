package com.example.gdemobile.ui.cargoList.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.BaseServiceCargoViewModel
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.utils.DateFormat
import com.example.gdemobile.utils.ExtensionFunction.Companion.fromJson
import com.example.gdemobile.utils.ExtensionFunction.Companion.getDate
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import java.time.format.DateTimeFormatter


class DocumentDetailsFragment : Fragment(), StateResponse {

    private lateinit var binding: FragmentDocumentDetailsBinding
    private lateinit var viewModel: BaseServiceCargoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewModel.stateResponse = this
        viewLifecycleOwner.lifecycleScope.launch {
           viewModel.createNewDocument()
        }
        binding = FragmentDocumentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun OnLoading() {

    }

    override fun OnError(message: String) {
        showToast(message)
    }

    override fun <Document> OnSucces(result: Document?) {
        showToast("Gotowe")
        var gson = Gson()
        var document = gson.fromJson<com.example.gdemobile.models.Document>(gson.toJson(result))
        binding.dokdefEdittext.setText(document.definition?.symbol)
        binding.contractorEdittext.setText(document.contractor?.name)
        binding.dateEdittext.setText(document.date.toString())
    }


}