package com.example.gdemobile.ui.cargoList.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.gdemobile.databinding.FragmentAmountCargoDialogBinding
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Currency
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast
import com.example.gdemobile.utils.NamesSharedVariable
import kotlinx.coroutines.launch


class AmountCargoDialog : DialogFragment(), StateResponse {

    private lateinit var binding: FragmentAmountCargoDialogBinding
    private lateinit var sharedViewModel: InssuingCargoListViewModel
    private var cargo: Cargo? = null
    private var idDocument: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        cargo = arguments?.getSerializable(NamesSharedVariable.cargo) as Cargo?
        idDocument = arguments?.getString(NamesSharedVariable.idDocument)
        binding = FragmentAmountCargoDialogBinding.inflate(inflater, container, false)
        binding.unitSpinner.setText(cargo?.mainUnit?.name)
        val unitAdapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            cargo?.additionalUnits!!.map { it -> it.name })
        binding.unitSpinner.setAdapter(unitAdapter)

        binding.currencysymbolSpinner.setText(Currency.symbols.first())
        val currencySymbolAdapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            Currency.symbols
        )
        binding.currencysymbolSpinner.setAdapter(currencySymbolAdapter)


        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sharedViewModel =
            ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        sharedViewModel.stateResponse = this


        binding.okButton.setOnClickListener {
            if (binding.amountEdittext.text?.length!! < 1)
                binding.amountEdittext.error = "Podaj ilość"
            else {
                lifecycleScope.launch {
                    sharedViewModel.addCargoOnDocument(
                        idDocument = idDocument,
                        idCargo = cargo?.id,
                        idUnit = cargo?.additionalUnits!!.first { it.name == binding.unitSpinner.text.toString() }.id,
                        amount = binding.amountEdittext.text.toString().toDouble(),
                        pricePerUnit = Currency(
                            binding.valueEdittext.text.toString().toDouble(),
                            binding.currencysymbolSpinner.text.toString()

                        )
                    )

                }
                blockDialog()
            }

        }

        return binding.root
    }


    fun blockDialog()
    {
        binding.amountEdittext.isClickable = false;
        binding.amountEdittext.isFocusable = false ;
        binding.currencysymbolSpinner.isClickable = false;
        binding.currencysymbolSpinner.isFocusable= false;
        binding.okButton.isClickable = false;
        binding.okButton.isFocusable = false;
        binding.unitSpinner.isClickable = false
        binding.valueEdittext.isActivated = false
        binding.valueEdittext.isFocusable = false;
    }

    override fun OnLoading() {

    }

    override fun OnError(message: String) {
        showToast(message)
        dialog?.dismiss()
    }

    override fun <T> OnSucces(result: T?) {
        showToast("Dodano towar")
        dialog?.dismiss()
    }


}