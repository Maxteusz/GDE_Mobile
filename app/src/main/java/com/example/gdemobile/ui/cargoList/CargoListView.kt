package com.example.gdemobile.ui.cargoList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.gdemobile.R
import com.example.gdemobile.config.Mode
import com.example.gdemobile.models.Contractor

class CargoListView : ViewModel() {
companion object {
    fun listContractor(): List<Contractor> {
        var list = listOf<Contractor>(
            Contractor("ffdfd"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
        )
        return list;
    }
}

    fun openActivity (context : Context, activity: Activity, view : View?)
    {
        val intent = Intent(context, activity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent)

    }
}