package com.example.gdemobile.ui.cargoList.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.gdemobile.databinding.FragmentAmountCargoDialogBinding
import com.example.gdemobile.models.Currency
import com.example.gdemobile.models.Quantity
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.viewmodels.DocumentPositionsViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ToastMessages
import kotlinx.coroutines.launch


class AmountCargoDialog : DialogFragment(), IStateResponse {

    private lateinit var binding: FragmentAmountCargoDialogBinding
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private lateinit var viewModel: DocumentPositionsViewModel

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
        binding = FragmentAmountCargoDialogBinding.inflate(inflater, container, false)
        setAdapters()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        viewModel = ViewModelProvider(requireActivity()).get(DocumentPositionsViewModel::class.java)
       viewModel.stateResponse = this
        binding.okButton.setOnClickListener {
            if (checkValidationViews())
                lifecycleScope.launch {
                    fillDocumentPositionInformation()
                   viewModel.addDocumentPosition(sharedViewModel.documentPosition.value!!,
                       sharedViewModel.document.value?.id!!)
                }
            blockDialog()
        }
        return binding.root
    }

    fun fillDocumentPositionInformation() {
       val documentPosition = sharedViewModel.documentPosition.value
       Log.i("FFFFF", sharedViewModel.documentPosition.value?.cargo?.name!!)
       documentPosition!!.amount = with(Quantity())
        {
            value = binding.amountEdittext.text.toString().toDouble()
            symbol = "szt"
            this
        }
        documentPosition.valuePerUnit = Currency(
            binding.valueEdittext.text.toString().toDouble(),
            binding.currencysymbolSpinner.text.toString()

        )
    }

    fun checkValidationViews(): Boolean {
        if (binding.amountEdittext.text?.length!! < 1) {
            binding.amountEdittext.error = "Podaj ilość"
            return false;
        } else if (binding.valueEdittext.text?.length!! < 1) {
            binding.valueEdittext.error = "Podaj cenę za sztukę"
            return false;
        }
        return true
    }

    fun setAdapters() {
        //documentPosition?.cargo = arguments?.getSerializable(NamesSharedVariable.cargo) as Cargo?
        binding.currencysymbolSpinner.setText(Currency.symbols.first())
        val currencySymbolAdapter = ArrayAdapter<String>(
            requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,
            Currency.symbols
        )
        binding.currencysymbolSpinner.setAdapter(currencySymbolAdapter)
    }

    fun blockDialog() {
        binding.amountEdittext.isClickable = false;
        binding.amountEdittext.isFocusable = false;
        binding.currencysymbolSpinner.isClickable = false;
        binding.currencysymbolSpinner.isFocusable = false;
        binding.okButton.isClickable = false;
        binding.okButton.isFocusable = false;
        binding.unitSpinner.isClickable = false
        binding.valueEdittext.isActivated = false
        binding.valueEdittext.isFocusable = false;
    }

    override fun OnLoading() {

    }

    override suspend fun OnError(message: String) {
        context?.let {
            CustomToast.showToast(
                it,
                message,
                CustomToast.Type.Error
            )
        }

    }

    override fun OnSucces() {
        context?.let {
            CustomToast.showToast(
                it,
                ToastMessages.correctCargoAdded,
                CustomToast.Type.Information
            )
        }
        sharedViewModel.unlockScanning()
        dismiss()



    }


}