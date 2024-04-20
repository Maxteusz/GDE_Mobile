package com.example.gdemobile.ui.dialogs.customdialog

import androidx.fragment.app.DialogFragment
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.ui.dialogs.customdialog.states.ICustomDialogState

class FinishAppState() : ICustomDialogState {

    override var message: String = "Czy zamknąć aplikację?"
    override var binding: ConfirmDialogBinding? = null
    override var fragment: DialogFragment? = null

    init {
        binding?.messageTextview?.text = message
    }

    override suspend fun onPositiveClickOn() {
        fragment?.activity?.finish()
    }
    override fun onNegativeClickOn() {
        fragment?.dismiss()
    }

    override fun onLoading() {

    }

    override suspend fun onError(message: String) {

    }

    override fun onSuccess() {

    }
}