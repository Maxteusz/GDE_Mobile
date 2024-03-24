package com.example.gdemobile.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gdemobile.databinding.FragmentDocumentPositionDetailsBinding
import com.example.gdemobile.models.Currency
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.viewmodels.SharedViewModel


class DocumentPositionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDocumentPositionDetailsBinding
    private lateinit var _documentPosition: DocumentPosition

    private val sharedViewModel: SharedViewModel by activityViewModels()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _documentPosition = sharedViewModel.documentPosition.value!!
        binding = FragmentDocumentPositionDetailsBinding.inflate(layoutInflater);
        initAdapters()
        binding.documentPosition = _documentPosition



        return binding.root
    }



    fun initAdapters() {
        val currencyAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.support_simple_spinner_dropdown_item,
            Currency.symbols
        )

        binding.currencysymbolSpinner.setAdapter(currencyAdapter)
        binding.currencysymbolSpinner.threshold = 100000



        val unitAdapter = ArrayAdapter(
             requireActivity(),
             R.layout.support_simple_spinner_dropdown_item,
            _documentPosition.cargo?.additionalUnits!!
        )
        binding.unitSpinner.threshold = 10000
         binding.unitSpinner.setAdapter(unitAdapter)
    }


}