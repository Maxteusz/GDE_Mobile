package com.example.gdemobile.ui.dialogs.customdialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.viewmodels.DocumentViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ToastMessages
import kotlinx.coroutines.launch


class CustomDialog(
    private val customDialogStates : ICustomDialogState
) : DialogFragment() {
    private lateinit var binding: ConfirmDialogBinding
    private lateinit var documentViewModel : DocumentViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()




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
        customDialogStates.binding = binding
        customDialogStates.fragment = this
        binding.confirmButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                customDialogStates.onPositiveClickOn()
            }

        }
        binding.dissmisButton.setOnClickListener { dismiss() }

        return binding.root
    }




}