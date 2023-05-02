package com.example.gdemobile.ui.cargoList

import android.util.Log
import androidx.lifecycle.*
import com.example.gdemobile.ApiInterface
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.config.Config
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.ui.StateResponse
import kotlinx.coroutines.*
import java.net.ConnectException
import java.net.SocketTimeoutException

class CargoListViewModel : ViewModel() {

    var stateResponse: StateResponse? = null
    private var _cargos = MutableLiveData<List<Cargo>?>(emptyList())
    private var _contractors = MutableLiveData<List<Contractor>?>(emptyList())
    private var _documentDefinitions = MutableLiveData<List<DocumentDefinition>?>(emptyList())
    private val _document: MutableLiveData<Document> = MutableLiveData<Document>(Document());
    val document: MutableLiveData<Document>
        get() = _document

    val documentDefinitions: MutableLiveData<List<DocumentDefinition>?>
        get() = _documentDefinitions

    val cargos: MutableLiveData<List<Cargo>?>
        get() = _cargos
    val contractors: MutableLiveData<List<Contractor>?>
        get() = _contractors


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

    fun getContractors() {
        stateResponse?.OnLoading()

        viewModelScope.launch {
            try {
                Log.i("Token",Config.tokenApi.toString())
                val quotesApi = RetrofitClient().getInstance().create(ApiInterface::class.java)
                val result = quotesApi.getContractors(Config.tokenApi!!)
                stateResponse?.OnSucces()
                _contractors.value = result.body()?.toMutableList()

            } catch (timeout: SocketTimeoutException) {
                stateResponse?.OnError()
                Log.e("SocketTimeoutException", timeout.message.toString())
            } catch (exception: ConnectException) {
                stateResponse?.OnError()
                Log.e("ConnectException", exception.message.toString())
            }
        }
    }

    fun getDocumentDefinitions() {
        stateResponse?.OnLoading()
        val quotesApi = RetrofitClient().getInstance().create(ApiInterface::class.java)
        viewModelScope.launch {
            try {
                val result = quotesApi.getDocumentDefinitions(Config.tokenApi!!)
                stateResponse?.OnSucces()
                _documentDefinitions.value = result.body()?.toMutableList()

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



