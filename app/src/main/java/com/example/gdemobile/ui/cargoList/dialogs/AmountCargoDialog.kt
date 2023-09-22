package com.example.gdemobile.ui.cargoList.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.gdemobile.databinding.FragmentAmountCargoDialogBinding
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.fragments.DocumentPositionListFragmentArgs
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast


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
        //var scannedCargoCode = arg.codeCargo



        getDialog()?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        sharedViewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        sharedViewModel.stateResponse = null
        binding = FragmentAmountCargoDialogBinding.inflate(inflater, container, false)

        binding.okButton.setOnClickListener {
            if(binding.amountEdittext.text?.length!! < 1)
                binding.amountEdittext.error = "Podaj ilość"
            else {
               // sharedViewModel.addCargoOnDocument(arg.idDocument,scannedCargoCode,binding.amountEdittext.text.toString().toDouble(), 21.0)
                this.showToast("Dodano towar")
                this.dismiss()
            }
        }

        return binding.root
    }


}