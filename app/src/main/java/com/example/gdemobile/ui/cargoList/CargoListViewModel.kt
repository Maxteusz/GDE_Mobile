package com.example.gdemobile.ui.cargoList

import android.util.Log
import androidx.lifecycle.*
import com.example.gdemobile.RetrofitMethod
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.config.Config
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.utils.LogTag
import kotlinx.coroutines.*
import okhttp3.internal.notify
import java.net.ConnectException
import java.net.SocketTimeoutException

class CargoListViewModel : ViewModel() {

    var stateResponse: StateResponse? = null
    private var _scannedCargo = MutableLiveData<List<DocumentPosition>?>(emptyList())
    private var _scannedCargoAfterFilter = MutableLiveData<List<DocumentPosition>?>(emptyList())
    private var _contractors = MutableLiveData<List<Contractor>?>(emptyList())

    private var _documentDefinitions = MutableLiveData<List<DocumentDefinition>?>(emptyList())
    private val _document: MutableLiveData<Document> = MutableLiveData<Document>(Document());
    val scannedBarcode: MutableLiveData<String> = MutableLiveData("")


    val document: LiveData<Document>
        get() = _document

    val documentDefinitions: LiveData<List<DocumentDefinition>?>
        get() = _documentDefinitions

    val scannedCargo: LiveData<List<DocumentPosition>?>
        get() = _scannedCargoAfterFilter


    val contractors: LiveData<List<Contractor>?>
        get() = _contractors


    fun addCargo(barcode: String, amount: Double = 1.0) {
        if (!barcode.isNullOrEmpty()) {
            _scannedCargo.value = _scannedCargo.value?.plus(
                DocumentPosition(
                    name = "Przykładowa nazwa",
                    unit = "szt.",
                    barcode = barcode,
                    code = "dsd",
                    amount = amount
                )
            )
            if (Config.aggregation)
              aggregatePosition()
            scannedBarcode.value = ""

        }


    }
    private fun aggregatePosition()
    {
        _scannedCargo.value =  _scannedCargo.value?.groupBy { it.barcode }?.
        map{ DocumentPosition(
            it.value.first().code,
            it.value.first().name,
            it.value.first().unit,
            it.value.first().barcode,
            it.value.sumOf { it.amount })
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
                val quotesApi = RetrofitClient().getInstance().create(RetrofitMethod::class.java)
                val result = quotesApi.getContractors(Config.tokenApi!!)
                if (result.code() == 200) {
                    stateResponse?.OnSucces()
                    _contractors.value = result.body()?.toMutableList()
                }

            } catch (timeout: SocketTimeoutException) {
                stateResponse?.OnError()
                Log.e(LogTag.timeoutException, timeout.message.toString())
            } catch (exception: ConnectException) {
                stateResponse?.OnError()
                Log.e(LogTag.connectException, exception.message.toString())
            } catch (exception: Exception) {
                stateResponse?.OnError()
                Log.e(LogTag.basicException, exception.message.toString())
            }
        }
    }

    fun getDocumentDefinitions() {
        stateResponse?.OnLoading()
        viewModelScope.launch {
            try {
                val quotesApi = RetrofitClient().getInstance().create(RetrofitMethod::class.java)
                val result = quotesApi.getAllDocumentDefinitions(Config.tokenApi!!)
                if (result.code() == 200) {
                    stateResponse?.OnSucces()
                    _documentDefinitions.value = result.body()?.toMutableList()
                }
            } catch (timeout: SocketTimeoutException) {
                stateResponse?.OnError()
                Log.e(LogTag.timeoutException, timeout.message.toString())
            } catch (exception: ConnectException) {
                stateResponse?.OnError()
                Log.e(LogTag.connectException, exception.message.toString())

            }
        }
    }


    fun filtrDocumentPosition(chars: String){
        _scannedCargoAfterFilter.value =  _scannedCargo.value?.filter {
            it.name.contains(chars, true)
        } ?: emptyList()

    }
}











