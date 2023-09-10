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
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch


open class BaseServiceCargoViewModel : ViewModel() {

    var stateResponse: StateResponse? = null
    private var _scannedCargo = MutableLiveData<List<DocumentPosition>?>(emptyList())
    private var _scannedCargoAfterFilter = MutableLiveData<List<DocumentPosition>?>(emptyList())
    private var _contractors = MutableLiveData<List<Contractor>?>(emptyList())
    private var _documentListInTemp = MutableLiveData<List<Document>?>(emptyList())

    private var _documentDefinitions = MutableLiveData<List<DocumentDefinition>?>(emptyList())
    private val _document: MutableLiveData<Document> = MutableLiveData<Document>(Document());
    val scannedBarcode: MutableLiveData<String> = MutableLiveData("")


    val document: LiveData<Document>
        get() = _document

    val documentDefinitions: LiveData<List<DocumentDefinition>?>
        get() = _documentDefinitions

    val scannedCargo: LiveData<List<DocumentPosition>?>
        get() = _scannedCargoAfterFilter
    val documentListInTemp: LiveData<List<Document>?>
        get() = _documentListInTemp

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
                    amount = amount,
                    id = "ddd"
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
                it.value.first().id,
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

    fun getDocumentsInTemp()  {
        viewModelScope.launch {
            val gson = Gson()
            val connection = ConnectService(stateResponse, GetDocumentsExternalPartyInTemp())
           var receiveList   = gson.fromJson(connection.makeConnectionForListData()!!,
               Array<Document>::class.java
           )
      //   Log.i("fdfd", receiveList.get(0).id )
           //  _documentListInTemp.postValue(receiveList)



        }
    }
    /*inline fun <reified T> fromJson(json: String?): T {
        val gs = Gson()
        val js = gs.toJson(model)
        categoriesModel = gs.fromJson<Any>(js, CategoriesModel::class.java)
    }*/

   /* internal inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)*/
}