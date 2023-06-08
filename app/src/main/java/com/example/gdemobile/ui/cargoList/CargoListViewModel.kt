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
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.StateResponse
import kotlinx.coroutines.*
import java.net.ConnectException
import java.net.SocketTimeoutException

class CargoListViewModel : ViewModel() {

    var stateResponse: StateResponse? = null
    private var _scannedCargo = MutableLiveData<List<DocumentPosition>?>(emptyList())
    private var _contractors = MutableLiveData<List<Contractor>?>(emptyList())
    private var _documentDefinitions = MutableLiveData<List<DocumentDefinition>?>(emptyList())
    private val _document: MutableLiveData<Document> = MutableLiveData<Document>(Document());
    val scannedBarcode: MutableLiveData<String> = MutableLiveData("")


    val document: MutableLiveData<Document>
        get() = _document

    val documentDefinitions: MutableLiveData<List<DocumentDefinition>?>
        get() = _documentDefinitions

    val scannedCargo: MutableLiveData<List<DocumentPosition>?>
        get() = _scannedCargo


    val contractors: MutableLiveData<List<Contractor>?>
        get() = _contractors


    fun addCargo(barcode: String, amount: Double = 1.0) {
        if (!barcode.isNullOrEmpty()) {

            val cargo = _scannedCargo.value?.toMutableList()
            cargo?.add(
                DocumentPosition(
                    name = "Przykładowa nazwa",
                    unit = "szt.",
                    barcode = barcode,
                    code = "dsd",
                    amount = amount
                )
            )
            if (Config.aggregation)
                _scannedCargo.postValue(cargo?.groupBy { it.barcode }?.map {
                    DocumentPosition(
                        it.value.first().code,
                        it.key,
                        it.value.first().unit,
                        it.value.first().barcode,
                        it.value.sumOf { it.amount })
                })
            else
                _scannedCargo.postValue(cargo)

        }


    }

    fun removeCargo(documentPosition: DocumentPosition, deleteAll: Boolean) {
        val updated = _scannedCargo.value?.toMutableList()
        if (deleteAll || documentPosition.amount <= 1)
            updated?.remove(documentPosition)
        else
            documentPosition.amount -= 1
        _scannedCargo.postValue(updated)

    }

    fun getContractors() {
        stateResponse?.OnLoading()
        viewModelScope.launch {
            try {
                val quotesApi = RetrofitClient().getInstance().create(ApiInterface::class.java)
                val result = quotesApi.getContractors(Config.tokenApi!!)
                if (result.code() == 200) {
                    stateResponse?.OnSucces()
                    _contractors.value = result.body()?.toMutableList()
                }

            } catch (timeout: SocketTimeoutException) {
                stateResponse?.OnError()
                Log.e("SocketTimeoutException", timeout.message.toString())
            } catch (exception: ConnectException) {
                stateResponse?.OnError()
                Log.e("ConnectException", exception.message.toString())
            } catch (exception: Exception) {
                stateResponse?.OnError()
                Log.e("ConnectException", exception.message.toString())
            }
        }
    }

    fun getDocumentDefinitions() {
        stateResponse?.OnLoading()
        viewModelScope.launch {
            try {
                val quotesApi = RetrofitClient().getInstance().create(ApiInterface::class.java)
                val result = quotesApi.getAllDocumentDefinitions(Config.tokenApi!!)
                if (result.code() == 200) {
                    stateResponse?.OnSucces()
                    _documentDefinitions.value = result.body()?.toMutableList()
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





