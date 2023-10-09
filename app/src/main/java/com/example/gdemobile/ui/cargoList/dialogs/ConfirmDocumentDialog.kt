package com.example.gdemobile.ui.cargoList.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast
import kotlinx.coroutines.launch


class ConfirmDocumentDialog : DialogFragment(), StateResponse {
    private lateinit var binding: ConfirmDialogBinding
    private lateinit var sharedViewModel: InssuingCargoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ConfirmDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sharedViewModel =
            ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        sharedViewModel.stateResponse = this
        binding.confirmButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                sharedViewModel.document.value?.id?.let { it1 -> sharedViewModel.confirmDocument(it1) }

            }

        }
        binding.dissmisButton.setOnClickListener { dismiss() }

        return binding.root
    }

    override fun OnLoading() {
        binding.messageTextview.setText("Zatwierdzanie dokumentu...")
        binding.dissmisButton.visibility = View.INVISIBLE
        binding.confirmButton.visibility = View.INVISIBLE


    }

    override fun OnError(message: String) {
        showToast(message)
        dismiss()

    }

    override fun <T> OnSucces(result: T?) {
        showToast("Dokument zatwierdzony")
        dismiss()
        findNavController().navigate(R.id.action_configmDocumentDialog_to_documentListFragment)

    }


}