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
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentAmountCargoDialogBinding
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Currency
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast
import com.example.gdemobile.utils.LogTag
import com.example.gdemobile.utils.NamesSharedVariable


class AmountCargoDialog : DialogFragment() {

    private lateinit var binding: FragmentAmountCargoDialogBinding
    private lateinit var sharedViewModel: InssuingCargoListViewModel
    private  var  cargo : Cargo? = null
    private var idDocument : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var bundle = Bundle();

        cargo = arguments?.getSerializable(NamesSharedVariable.cargo) as Cargo?
        idDocument = arguments?.getString(NamesSharedVariable.idDocument)


        getDialog()?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        sharedViewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        sharedViewModel.stateResponse = null
        binding = FragmentAmountCargoDialogBinding.inflate(inflater, container, false)

        binding.okButton.setOnClickListener {
            if(binding.amountEdittext.text?.length!! < 1)
                binding.amountEdittext.error = "Podaj ilość"
            else {
                Config.port = "7000"
                sharedViewModel.addCargoOnDocument(
                    idDocument = idDocument,
                    idCargo = cargo?.id,
                    idUnit = "00000000-0011-0007-0007-000000000000",
                    amount = 2.0,
                    pricePerUnit = Currency(2.0,"PLN")
                )

                this.showToast("Dodano towar")
                this.dismiss()
            }
        }

        return binding.root
    }


}