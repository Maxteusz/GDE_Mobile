package com.example.gdemobile.ui.cargoList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.ConnectService
import com.example.gdemobile.apiConnect.enovaConnect.methods.AddNewCargoToDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetCargoInformation
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetDocumentPositionsOnDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetDocumentsExternalPartyInTemp
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.models.Currency
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.StateResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch


open class BaseServiceCargoViewModel : ViewModel() {

    var stateResponse: StateResponse? = null
    private var _scannedCargo = MutableLiveData<List<DocumentPosition>?>(emptyList())
    private var _scannedCargoAfterFilter = MutableLiveData<List<DocumentPosition>?>(emptyList())
    private var _contractors = MutableLiveData<List<Contractor>?>(emptyList())
    private var _documentListInTemp = MutableLiveData<List<Document>>(emptyList())
    private var _documentPositions = MutableLiveData<List<DocumentPosition>>(emptyList())

    private var _documentDefinitions = MutableLiveData<List<DocumentDefinition>?>(emptyList())

    val scannedBarcode: MutableLiveData<String> = MutableLiveData("")


    val documentDefinitions: LiveData<List<DocumentDefinition>?>
        get() = _documentDefinitions

    val documentPositions: LiveData<List<DocumentPosition>?>
        get() = _documentPositions

    val scannedCargo: LiveData<List<DocumentPosition>?>
        get() = _scannedCargoAfterFilter
    val documentListInTemp: LiveData<List<Document>>
        get() = _documentListInTemp

    val contractors: LiveData<List<Contractor>?>
        get() = _contractors


    fun addCargo(barcode: String, amount: Double = 1.0) {
        if (!barcode.isNullOrEmpty()) {
            //getCargo()
            /* _scannedCargo.value = _scannedCargo.value?.plus(
                 DocumentPosition(
                     name = "Przykładowa nazwa",
                     unit = "szt.",
                     barcode = barcode,
                     code = "dsd",
                     amount = amount,
                     id = "ddd"
                 )*/
            //)


        }
    }

    fun addCargoOnDocument(
        idDocument: String,
        cargoCode: String,
        amount: Double,
        pricePerUnit: Double
    ) {
        viewModelScope.launch {
            val gson = Gson()
            val connection = ConnectService(stateResponse)
            connection.makeConnection<String>(
                AddNewCargoToDocument(
                    idDocument,
                    cargoCode,
                    "",
                    amount,
                    Currency(pricePerUnit, "PLN")
                )
            )
        }

    }


    fun removeCargo(documentPosition: DocumentPosition, deleteAll: Boolean) {

        if (deleteAll || documentPosition.amount <= 1)
            _scannedCargo.value = _scannedCargo.value?.minus(documentPosition)
        else
            documentPosition.amount -= 1
        _scannedCargoAfterFilter.postValue(_scannedCargo.value)

    }


    fun filtrDocumentPosition(chars: String) {
        _scannedCargoAfterFilter.value = _scannedCargo.value?.filter {
            it.name.contains(chars, true) ||
                    it.barcode.contains(chars, true)
        } ?: emptyList()

    }

    fun getCargo(name: String = "bikini") {
        /*   viewModelScope.launch {
               val connection = ConnectService(stateResponse, GetDocumentsExternalPartyInTemp())
               connection.makeConnectionForListData<Document>()

           }*/
    }

    suspend fun getDocumentsInTemp() {
        viewModelScope.run {
            val gson = Gson()
            val connection = ConnectService(stateResponse)
            var receiveList = connection.makeConnection<List<Document>>(
                GetDocumentsExternalPartyInTemp()
            )
            if (receiveList != null) {
                val convertedList = gson.fromJson<List<Document>>(gson.toJson(receiveList))
                _documentListInTemp.postValue((convertedList))
            }
        }
    }

    suspend fun getDocumentPositions(idDocument: String) {
        _documentPositions.postValue(emptyList())
        viewModelScope.run {
            val gson = Gson()
            val connection = ConnectService(stateResponse)
            var receiveList = connection.makeConnection<List<DocumentPosition>>(
                GetDocumentPositionsOnDocument(idDocument)
            )
            if (receiveList != null) {
                val convertedList = gson.fromJson<List<DocumentPosition>>(gson.toJson(receiveList))
                _documentPositions.postValue(convertedList)

            }
        }
    }

    suspend fun getCargoInformation(idCargo: String) :Cargo? {
        viewModelScope.run {
            val gson = Gson()
            val connection = ConnectService(stateResponse)
            var receiveList = connection.makeConnection<Any>(
                GetCargoInformation(idCargo)
            )
            if (receiveList != null) {
                val cargo = gson.fromJson<Cargo>(gson.toJson(receiveList))
                Log.i("GetCargo", cargo.name)
                return  cargo
            }
            return null
        }
        return null

    }


    internal inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)
}


