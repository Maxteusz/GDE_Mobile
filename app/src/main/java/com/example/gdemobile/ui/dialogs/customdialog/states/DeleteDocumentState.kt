package com.example.gdemobile.ui.dialogs.customdialog.states

import android.annotation.SuppressLint
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.viewmodels.DocumentPositionsViewModel
import com.example.gdemobile.ui.viewmodels.DocumentViewModel
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ToastMessages

class DeleteDocumentState(val document : Document) : ICustomDialogState {
    override var binding: ConfirmDialogBinding? = null

    override var fragment: DialogFragment? = null

    override var message: String = "Czy usunąć dokument?"


    override suspend fun onPositiveClickOn() {
        DocumentViewModel().apply {
            stateResponse = this@DeleteDocumentState
        }.deleteDocument(document)
    }

    override fun onNegativeClickOn() {
        fragment?.findNavController()?.popBackStack()
        fragment?.dismiss()
    }

    override fun onLoading() {
        binding?.confirmButton?.isClickable = false
        binding?.dissmisButton?.isClickable = false

    }

    override suspend fun onError(message: String) {
        fragment?.let {
            CustomToast.showToast(
                it.requireActivity(),
                message,
                CustomToast.Type.Error
            )
        }
        fragment?.dismiss()
    }

    @SuppressLint("RestrictedApi")
    override fun onSuccess() {
        fragment?.let {
            CustomToast.showToast(
                it.requireActivity(),
                ToastMessages.correctDocumentPositionDelete,
                CustomToast.Type.Information
            )
            fragment?.dismiss()
            fragment?.findNavController()?.popBackStack()
        }
    }
}