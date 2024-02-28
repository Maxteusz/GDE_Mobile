package com.example.gdemobile.ui.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.viewmodels.DocumentViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ToastMessages
import kotlinx.coroutines.launch


class ConfirmDocumentDialog : DialogFragment(), IStateResponse {
    private lateinit var binding: com.example.gdemobile.databinding.ConfirmDialogBinding
    private lateinit var documentViewModel : DocumentViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

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
        documentViewModel =
            ViewModelProvider(requireActivity()).get(DocumentViewModel::class.java)
        documentViewModel.stateResponse = this
        binding.confirmButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                documentViewModel.confirmDocument(sharedViewModel.document.value!!)


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