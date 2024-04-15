package com.example.gdemobile.ui.dialogs.customdialog

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
import com.example.gdemobile.ui.dialogs.customdialog.states.ICustomDialogState
import com.example.gdemobile.ui.viewmodels.DocumentViewModel
import kotlinx.coroutines.launch


class CustomDialog(
    private val customDialogStates : ICustomDialogState?
) : DialogFragment() {
    private lateinit var binding: ConfirmDialogBinding
    private lateinit var documentViewModel : DocumentViewModel

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
        customDialogStates?.binding = binding
        customDialogStates?.fragment = this
        binding.messageTextview.text = customDialogStates?.message
        binding.confirmButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                customDialogStates?.onPositiveClickOn()
            }

        }
        binding.dissmisButton.setOnClickListener { dismiss() }

        return binding.root
    }




}