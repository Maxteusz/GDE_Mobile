package com.example.gdemobile.utils

import android.app.DatePickerDialog
import android.widget.EditText
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

open class ExtensionFunction {
    companion object {
        fun DatePickerDialog.getDate() : String{
            val da = LocalDate.of(datePicker.year,datePicker.month,datePicker.dayOfMonth)
            val date = da.format(DateTimeFormatter.ofPattern(DateFormat.SIMPLE_DATE))
            return date

        }

    }
}