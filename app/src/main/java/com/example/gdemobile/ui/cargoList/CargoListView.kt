package com.example.gdemobile.ui.cargoList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Contractor
import okhttp3.internal.filterList

class CargoListView : ViewModel() {


    private var _cargos = MutableLiveData<List<Cargo>>(emptyList())
    val cargos: LiveData<List<Cargo>>
        get() =  _cargos



    fun openActivity(context: Context, activity: Activity, finishActivity: Boolean = false) {
        val intent = Intent(context, activity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent)
        if (finishActivity) {
            val activity = context as Activity
            activity.finish()
        }
    }

    fun addCargo(barcode: String) {
        if(!barcode.isNullOrEmpty()) {
            val cargo = cargos.value?.toMutableList()
            cargo?.add(Cargo(name = "Przykładowa nazwa", barcode = barcode))
            _cargos.postValue(cargo)
        }
    }

    fun getCargo(): List<Cargo>? {
        return _cargos.value
    }




}