package com.example.gdemobile.ui.cargoList.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.gdemobile.databinding.FragmentAmountCargoDialogBinding
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel


class AmountCargoDialog : DialogFragment() {

    private lateinit var binding: FragmentAmountCargoDialogBinding
    private lateinit var sharedViewModel: InssuingCargoListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getDialog()?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        sharedViewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        binding = FragmentAmountCargoDialogBinding.inflate(inflater, container, false)

        binding.okButton.setOnClickListener {
            if(binding.amountEdittext.text?.length!! < 1)
                binding.amountEdittext.error = "Podaj ilość"
            else {
                sharedViewModel.addCargo(
                    sharedViewModel.scannedBarcode.value!!,
                    binding.amountEdittext.text.toString().toDouble()
                )
                this.dismiss()
            }
        }

        return binding.root
    }


}