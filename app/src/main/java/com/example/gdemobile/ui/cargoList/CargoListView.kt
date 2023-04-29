package com.example.gdemobile.ui.cargoList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gdemobile.ApiInterface
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.config.Config
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.ui.StateResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import okhttp3.internal.filterList
import okio.Timeout
import org.json.JSONObject
import retrofit2.create
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

class CargoListView : ViewModel() {

    var stateResponse : StateResponse? = null
    private var _cargos = MutableLiveData<List<Cargo>?>(emptyList())
    private var _contractors = MutableLiveData<List<Contractor>?>(emptyList())
    private var timeCount: Int = 0
    val cargos: MutableLiveData<List<Cargo>?>
        get() = _cargos
    val contracotrs: MutableLiveData<List<Contractor>?>
        get() = _contractors
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

   /* fun getCargo(): List<Cargo>? {
        return _cargos.value
    }

    fun resetCount() {
        timeCount = 0
    }*/

    private suspend fun startCount() {
        timeCount++
        delay(1000L)
        if (timeCount > 5)
            _clearedFocus.value = true
        else
            _clearedFocus.value = false
    }

    fun getContractors()
    {
        stateResponse?.OnLoading()
        val quotesApi = RetrofitClient().getInstance().create(ApiInterface::class.java)
       viewModelScope.launch {
            try {
                val result = quotesApi.getContractors(Config.tokenApi)
                if (result != null) {
                    stateResponse?.OnSucces()
                    Log.i("GetContractors", result.body().toString())
                }
            } catch (timeout: SocketTimeoutException) {
                stateResponse?.OnError()
                Log.e("SocketTimeoutException", timeout.message.toString())
            }
            catch (exception: ConnectException) {
                stateResponse?.OnError()
                Log.e("ConnectException", exception.message.toString())
            }
        }
    }


    
}



