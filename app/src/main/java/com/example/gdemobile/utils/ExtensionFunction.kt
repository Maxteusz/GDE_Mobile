package com.example.gdemobile.utils

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

open class ExtensionFunction {
    companion object {

        fun Fragment.showToast(message: String) =
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

         inline fun <reified T> Gson.fromJson(json: String) =
            fromJson<T>(json, object : TypeToken<T>() {}.type)
    }
}