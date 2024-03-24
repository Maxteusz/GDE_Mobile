package com.example.gdemobile.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.gdemobile.databinding.FragmentDocumentPositionDetailsBinding
import com.example.gdemobile.models.Currency


class DocumentPositionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDocumentPositionDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDocumentPositionDetailsBinding.inflate(layoutInflater);





        /* val unitAdapter = ArrayAdapter<String>(
             requireContext(),
             androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
             documentPosition.cargo?.additionalUnits!!.map { it -> it.name })
         binding.unitSpinner.setAdapter(unitAdapter)*/

        val currencyAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            Currency.symbols
        )
        binding.currencysymbolSpinner.setAdapter(currencyAdapter)



        return binding.root
    }


}