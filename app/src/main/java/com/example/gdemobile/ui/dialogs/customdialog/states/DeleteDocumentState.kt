package com.example.gdemobile.ui.dialogs.customdialog.states

import androidx.fragment.app.DialogFragment
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.viewmodels.DocumentPositionsViewModel
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ToastMessages

class DeleteDocumentState(val document : Document) : ICustomDialogState {
    override var binding: ConfirmDialogBinding? = null

    override var fragment: DialogFragment? = null

    override var message: String = "Czy usunąć dokument?"


    override suspend fun onPositiveClickOn() {
        DocumentPositionsViewModel().apply {
            stateResponse = this@DeleteDocumentState
        }.deleteDocumentPosition(document.id)
    }

    override fun onLoading() {

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

    override fun onSuccess() {
        fragment?.let {
            CustomToast.showToast(
                it.requireActivity(),
                ToastMessages.correctDocumentPositionDelete,
                CustomToast.Type.Information
            )
        }
    }
}