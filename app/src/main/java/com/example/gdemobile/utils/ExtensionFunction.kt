package com.example.gdemobile.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

open class ExtensionFunction {
    companion object {
        fun DatePickerDialog.getDate() : LocalDate{
            val da = LocalDate.of(datePicker.year,datePicker.month,datePicker.dayOfMonth)
            return da

        }

        fun Fragment.showToast(message : String) =  Toast.makeText(activity?.baseContext,message, Toast.LENGTH_SHORT).show();


    }
}