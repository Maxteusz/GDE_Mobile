package com.example.gdemobile.ui.dialogs.customdialog.states

import androidx.fragment.app.DialogFragment
import com.example.gdemobile.databinding.ConfirmDialogBinding

class FinishAppDialog : ICustomDialogState {
    override var binding: ConfirmDialogBinding? = null

    override var fragment: DialogFragment? = null
    override var message: String = "Czy zamknąć aplikację?"



    override suspend fun onPositiveClickOn() {
        fragment?.activity?.finish()
    }

    override fun onLoading() {

    }

    override suspend fun onError(message: String) {

    }

    override fun onSuccess() {

    }
}