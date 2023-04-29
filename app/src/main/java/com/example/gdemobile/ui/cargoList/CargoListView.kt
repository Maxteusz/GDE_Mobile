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

    fun getToken() {
            val quotesApi = RetrofitClient().getInstance().create(ApiInterface::class.java)
            val map: HashMap<String, String> =
                hashMapOf("login" to Config.usernameERP, "password" to Config.passwordERP)
        Log.i("Json", map.toString())
            GlobalScope.launch {
                try {
                    val result = quotesApi.getToken(map)
                    if (result != null) {
                       // Config.tokenApi = result.body().toString()
                         Log.i("GetToken", result.code().toString())
                    }
                } catch (timeout: SocketTimeoutException) {
                    Log.e("SocketTimeoutException", timeout.message.toString())
                }
                catch (exception: ConnectException) {
                    Log.e("ConnectException", exception.message.toString())
                }


            }
    }
}



