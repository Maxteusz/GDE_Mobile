package com.example.gdemobile.ui.cargoList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.config.Config
import com.example.gdemobile.enovaConnect.ConnectService
import com.example.gdemobile.enovaConnect.methods.GetDocumentsExternalPartyInTemp
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.utils.LogTag
import kotlinx.coroutines.launch

open class BaseServiceCargoViewModel : ViewModel() {

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
            //getCargo()
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

    private fun aggregatePosition() {
        _scannedCargo.value = _scannedCargo.value?.groupBy { it.barcode }?.map {
            DocumentPosition(
                it.value.first().code,
                it.value.first().name,
                it.value.first().unit,
                it.value.first().barcode,
                it.value.sumOf { it.amount })
        }
    }

    fun removeCargo(documentPosition: DocumentPosition, deleteAll: Boolean) {

        if (deleteAll || documentPosition.amount <= 1)
            _scannedCargo.value = _scannedCargo.value?.minus(documentPosition)
        else
            documentPosition.amount -= 1
        _scannedCargoAfterFilter.postValue(_scannedCargo.value)

        Log.i(LogTag.amountDocumentPosition, documentPosition.amount.toString())


    }

    /*fun getContractors() {
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
    }*/

    /*fun getDocumentDefinitions() {
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
    }*/

    fun filtrDocumentPosition(chars: String) {
        _scannedCargoAfterFilter.value = _scannedCargo.value?.filter {
            it.name.contains(chars, true) ||
                    it.barcode.contains(chars, true)
        } ?: emptyList()

    }

   /* fun getCargo(name: String = "bikini"): DocumentPosition? {
        viewModelScope.async(Dispatchers.IO) {
            // try {
            val quotesApi = RetrofitClient().getInstance().create(RetrofitMethod::class.java)
            val result = quotesApi.getCargoFromApi(Config.tokenApi!!, name)
            Log.i(LogTag.cargoDownloadFromApi, result.code().toString())
            if (result.code() == 200) {
                Log.i(LogTag.cargoDownloadFromApi, result.body().toString())
                // stateResponse?.OnSucces()
                return@async result.body()
            } else async@ null


            *//*  } catch (timeout: SocketTimeoutException) {
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
    return null*//*
        }
        return null
    }*/

    /*   fun <T : Any> getDocumentInTemp(method : RetrofitMethod) : ReceiveDto<T> {
           stateResponse?.OnLoading()
           viewModelScope.launch {
               try {
                   val quotesApi = RetrofitClient().getInstance().create(RetrofitMethod::class.java)

                   val body = RequestDto<PaginationDto>()
                   body.databaseHanlde = "APIGDE"
                   body.methodName =
                       InternalAdmissionDocumentsConnectInformation.getDocumentsExternalPartyInTemp
                   body.serviceName = InternalAdmissionDocumentsConnectInformation.serviceName
                   body.methodArgsDto = RequestDto.MethodArgs()
                   body.methodArgsDto?.dto?.pagination = PaginationDto(0, 3)
                   val result =
                   if (result.code() == 200) {
                       Log.i("Resluttt", )
                       stateResponse?.OnSucces()
                   }
               } catch (timeout: SocketTimeoutException) {
                   stateResponse?.OnError()
                   Log.e(LogTag.timeoutException, timeout.message.toString())
               } catch (exception: ConnectException) {
                   stateResponse?.OnError()
                   Log.e(LogTag.connectException, exception.message.toString())
               } catch (exception: java.lang.Exception) {
                   stateResponse?.OnError()
                   Log.e(LogTag.unknownException, exception.message.toString())
               }


           }
       }*/
    fun getDocumentInTemp() {
        stateResponse?.OnLoading()
        viewModelScope.launch {
            val connection = ConnectService(stateResponse, GetDocumentsExternalPartyInTemp())
            connection.makeConnection<Document>()
        }
    }
}