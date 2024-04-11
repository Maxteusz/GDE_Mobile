package com.example.gdemobile.ui.dialogs.customdialog

import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.viewmodels.DocumentViewModel
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ToastMessages

class ConfirmDocumentState(val document: Document) :
    ICustomDialogState {

private var message = "Czy chcesz zatwierdzic dokument?"
    override var binding: ConfirmDialogBinding? = null
    override var fragment: DialogFragment? = null
    override suspend fun onPositiveClickOn() {
        DocumentViewModel().apply {
            stateResponse = this@ConfirmDocumentState
        }.confirmDocument(document)
    }

    init {
        binding?.messageTextview?.text = message
    }

    override fun onLoading() {
        binding?.messageTextview?.setText("Zatwierdzanie dokumentu...")
        binding?.dissmisButton?.visibility = View.INVISIBLE
        binding?.confirmButton?.visibility = View.INVISIBLE
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
                ToastMessages.correctConfimDocument,
                CustomToast.Type.Information
            )
        }
        fragment?.findNavController()
            ?.navigate(R.id.action_documentPostionListFragment_to_menuFragment)
    }


}