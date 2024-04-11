package com.example.gdemobile.ui.dialogs.customdialog

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.gdemobile.databinding.ConfirmDialogBinding
import com.example.gdemobile.ui.IStateResponse

interface ICustomDialogState : IStateResponse {
    var binding : ConfirmDialogBinding?
    var fragment : DialogFragment?
    suspend fun onPositiveClickOn()
}