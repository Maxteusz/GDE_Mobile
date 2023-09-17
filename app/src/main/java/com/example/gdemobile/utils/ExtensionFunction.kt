package com.example.gdemobile.utils

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.time.LocalDate

open class ExtensionFunction {
    companion object {
        fun DatePickerDialog.getDate(): LocalDate {
            val da = LocalDate.of(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            return da
        }
        fun Fragment.showToast(message: String) =
            Toast.makeText(activity?.baseContext, message, Toast.LENGTH_SHORT).show();
    }
}