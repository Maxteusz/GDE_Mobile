package com.example.gdemobile.ui.cargoList

import android.util.Log
import androidx.lifecycle.*
import com.example.gdemobile.ApiInterface
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.config.Config
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.ui.StateResponse
import kotlinx.coroutines.*
import java.net.ConnectException
import java.net.SocketTimeoutException

class CargoListView : ViewModel() {

    var stateResponse: StateResponse? = null
    private var _cargos = MutableLiveData<List<Cargo>?>(emptyList())
    private var _contractors = MutableLiveData<List<Contractor>?>(emptyList())
    private var timeCount: Int = 0
    val cargos: MutableLiveData<List<Cargo>?>
        get() = _cargos
    val contractors: MutableLiveData<List<Contractor>?>
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
            cargo?.add(
                Cargo(
                    Id = "kjkj",
                    Name = "Przykładowa nazwa",
                    Barcode = barcode,
                    Code = "dsds"
                )
            )
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

    fun getContractors() {
        stateResponse?.OnLoading()
        val quotesApi = RetrofitClient().getInstance().create(ApiInterface::class.java)
        viewModelScope.launch {
            try {
                val result = quotesApi.getContractors(Config.tokenApi)
                if (result != null) {
                    stateResponse?.OnSucces()
                    _contractors.value = result.body()?.toMutableList()
                }
            } catch (timeout: SocketTimeoutException) {
                stateResponse?.OnError()
                Log.e("SocketTimeoutException", timeout.message.toString())
            } catch (exception: ConnectException) {
                stateResponse?.OnError()
                Log.e("ConnectException", exception.message.toString())
            }
        }
    }


}



