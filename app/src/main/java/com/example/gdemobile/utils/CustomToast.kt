package com.example.gdemobile.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.gdemobile.R
import com.example.gdemobile.databinding.CustomToastLayoutBinding

object CustomToast {

    fun showToast(context: Context, message: String, type: Type) {

        val binding: CustomToastLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.custom_toast_layout,
            null,
            false
        )
        binding.toastMessage = message
        val layout: View = binding.root
        val toast = Toast(context)
        if (type == Type.Warning) {
            toast.duration = Toast.LENGTH_LONG
            binding.icon.setImageResource(R.drawable.warning_icon)
        }
        if (type == Type.Information) {
            toast.duration = Toast.LENGTH_SHORT
            binding.icon.setImageResource(R.drawable.information_icon)
        }
        if (type == Type.Error) {
            toast.duration = Toast.LENGTH_LONG
            binding.icon.setImageResource(R.drawable.error_icon)
        }

        toast.view = layout
        toast.show()
    }

    enum class Type {
        Information,
        Warning,
        Error
    }

}