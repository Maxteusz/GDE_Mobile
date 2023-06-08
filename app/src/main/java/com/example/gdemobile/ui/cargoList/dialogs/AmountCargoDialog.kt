package com.example.gdemobile.ui.cargoList.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentAmountCargoDialogBinding
import com.example.gdemobile.ui.cargoList.CargoListViewModel


class AmountCargoDialog : DialogFragment() {

    private lateinit var binding: FragmentAmountCargoDialogBinding
    private lateinit var sharedViewModel: CargoListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getDialog()?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        sharedViewModel = ViewModelProvider(requireActivity()).get(CargoListViewModel::class.java)
        binding = FragmentAmountCargoDialogBinding.inflate(inflater, container, false)

        binding.okButton.setOnClickListener {
            Log.i("{{Test}}", sharedViewModel.scannedBarcode.value!!)
            sharedViewModel.addCargo(
                sharedViewModel.scannedBarcode.value!!,
                binding.amountEdittext.text.toString().toDouble()
            )
            Log.i("{{Test}}", sharedViewModel.scannedCargo.value?.size.toString())
            this.dismiss()
        }

        return binding.root
    }


}