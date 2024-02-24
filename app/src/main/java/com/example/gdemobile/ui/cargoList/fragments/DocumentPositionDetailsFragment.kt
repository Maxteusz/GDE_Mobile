package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gdemobile.databinding.FragmentDocumentPositionDetailsBinding
import com.example.gdemobile.models.Currency
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.cargoList.BaseServiceCargoViewModel
import com.example.gdemobile.utils.NamesSharedVariable


class DocumentPositionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDocumentPositionDetailsBinding
    private lateinit var viewModel: BaseServiceCargoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDocumentPositionDetailsBinding.inflate(layoutInflater);
        val documentPosition =
            arguments?.getSerializable(NamesSharedVariable.documentPosition) as DocumentPosition



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

        binding.amountEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {

            }
        })

        return binding.root
    }


}