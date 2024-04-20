package com.example.gdemobile.ui.dialogs.customdialog.states

import androidx.fragment.app.DialogFragment
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.viewmodels.DocumentPositionsViewModel
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ToastMessages

class DeleteDocumentPositionState(val documentPosition : DocumentPosition) : ICustomDialogState{
    override var binding: ConfirmDialogBinding? = null

    override var fragment: DialogFragment? =null

    override var message: String = "Czy usunąć pozycje: ${documentPosition.cargo?.code}"


    override suspend fun onPositiveClickOn() {
        DocumentPositionsViewModel().apply {
            stateResponse = this@DeleteDocumentPositionState
        }.deleteDocumentPosition(documentPosition.id)
    }
    override fun onNegativeClickOn() {
        fragment?.dismiss()
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