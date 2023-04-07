package com.example.gdemobile.ui.cargoList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Contractor
import kotlinx.coroutines.*
import okhttp3.internal.filterList

class CargoListView : ViewModel() {


    private var _cargos = MutableLiveData<List<Cargo>?>(emptyList())
    private var timeCount: Int = 0
    val cargos: MutableLiveData<List<Cargo>?>
        get() = _cargos
    private var _clearedFocus = MutableLiveData<Boolean>()
    val clearedFocus: LiveData<Boolean>
        get() = _clearedFocus

    init {
        viewModelScope.launch(Dispatchers.Main) {
            while (viewModelScope.isActive)
                startCount()
        }
    }


    fun addCargo(barcode: String) {
        if (!barcode.isNullOrEmpty()) {
            val cargo = cargos.value?.toMutableList()
            cargo?.add(Cargo(Id = cargos.value?.size!!, Name = "Przykładowa nazwa", Barcode = barcode, Code = "dsds"))
            _cargos.postValue(cargo)
        }
    }

    fun getCargo(): List<Cargo>? {
        return _cargos.value
    }

    fun resetCount() {
        timeCount = 0
    }

    private suspend fun startCount() {
        timeCount++
        delay(1000L)
        if (timeCount > 5)
            _clearedFocus.value = true
        else
            _clearedFocus.value = false
    }
}




