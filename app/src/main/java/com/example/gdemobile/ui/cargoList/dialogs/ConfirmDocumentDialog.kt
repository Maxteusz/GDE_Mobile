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
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ToastMessages
import kotlinx.coroutines.launch


class ConfirmDocumentDialog : DialogFragment(), IStateResponse {
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

    override suspend fun OnError(message: String) {
        context?.let { CustomToast.showToast(it,message, CustomToast.Type.Error) }
        dismiss()

    }

    override fun  OnSucces() {
        context?.let { CustomToast.showToast(it,ToastMessages.correctConfimDocument,CustomToast.Type.Information) }
        dismiss()
        activity?.finish()


    }


}