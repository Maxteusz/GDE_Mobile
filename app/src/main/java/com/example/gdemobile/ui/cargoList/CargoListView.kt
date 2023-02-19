package com.example.gdemobile.ui.cargoList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.utils.Utils
import kotlin.coroutines.coroutineContext

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

    private val _cargos = MutableLiveData<List<Cargo>>()
    val cargos: LiveData<List<Cargo>>
        get() = _cargos

    fun openActivity(context: Context, activity: Activity, view: View?) {
        val intent = Intent(context, activity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent)

    }

    fun addCargo(context: Context,barcode: String) {
        val cargo = cargos.value?.toMutableList()
        Log.i("Cargo", cargo?.size.toString())
        cargo?.add(Cargo(barcode,barcode))
        Log.i("Cargo", cargo?.size.toString())
        Utils.showToast(context,cargo?.last()?.name!!.toString())


        _cargos.value = cargo
    }

    fun getCargo()
    {
        val cargo = listOf(

            Cargo("3232","3232"),
        )


        _cargos.value = cargo
    }


}