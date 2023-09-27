package com.example.gdemobile.ui.cargoList.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentAmountCargoDialogBinding
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Currency
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast
import com.example.gdemobile.utils.LogTag
import com.example.gdemobile.utils.NamesSharedVariable
import kotlinx.coroutines.launch


class AmountCargoDialog : DialogFragment(), StateResponse {

    private lateinit var binding: FragmentAmountCargoDialogBinding
    private lateinit var sharedViewModel: InssuingCargoListViewModel
    private var cargo: Cargo? = null
    private var idDocument: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        cargo = arguments?.getSerializable(NamesSharedVariable.cargo) as Cargo?
        idDocument = arguments?.getString(NamesSharedVariable.idDocument)


        getDialog()?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        sharedViewModel =
            ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        sharedViewModel.stateResponse = this
        binding = FragmentAmountCargoDialogBinding.inflate(inflater, container, false)

        binding.okButton.setOnClickListener {
            if (binding.amountEdittext.text?.length!! < 1)
                binding.amountEdittext.error = "Podaj ilość"
            else {
               lifecycleScope.launch {
                    sharedViewModel.addCargoOnDocument(
                        idDocument = idDocument,
                        idCargo = cargo?.id,
                        idUnit = cargo?.mainUnit?.id,
                        amount = binding.amountEdittext.text.toString().toDouble(),
                        pricePerUnit = Currency(
                            binding.valueEdittext.text.toString().toDouble(),
                            "PLN"
                        )
                    )}
               }

        }

        return binding.root
    }

    override fun OnLoading() {

    }

    override fun OnError(message: String) {
        showToast("Wystąpił błąd")
        dialog?.dismiss()
    }

    override fun <T> OnSucces(result: T?) {
        showToast("Dodano towar")
        dialog?.dismiss()
    }


}