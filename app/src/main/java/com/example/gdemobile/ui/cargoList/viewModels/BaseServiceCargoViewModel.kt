package com.example.gdemobile.ui.cargoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.ConnectService
import com.example.gdemobile.apiConnect.enovaConnect.methods.AddNewCargoToDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.ConfirmDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.CreateNewDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetCargoByEAN
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetContractors
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetDocumentDefinitions
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetDocumentPositionsOnDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetDocumentsExternalPartyInTemp
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.models.Currency
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.utils.ExtensionFunction.Companion.fromJson
import com.google.gson.Gson
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch


open class BaseServiceCargoViewModel : ViewModel() {

    private var lastQueryTime: Long = 0
    var stateResponse: StateResponse? = null
    private var _document = MutableLiveData(Document())

    private val _scannedCargoCopy = mutableListOf<DocumentPosition>()


    private var _contractors = MutableLiveData<List<Contractor>?>(emptyList())
    private var _documentListInTemp = MutableLiveData<List<Document>>(emptyList())

    private var _documentDefinitions = MutableLiveData<List<DocumentDefinition>?>(emptyList())


    val scannedBarcode: MutableLiveData<String> = MutableLiveData("")


    val documentDefinitions: LiveData<List<DocumentDefinition>?>
        get() = _documentDefinitions

    var document: MutableLiveData<Document>
        get() = _document
        set(value) {
            _document.value = value.value
        }


    val documentListInTemp: LiveData<List<Document>>
        get() = _documentListInTemp

    val contractors: LiveData<List<Contractor>?>
        get() = _contractors


    suspend fun addCargoOnDocument(
        idDocument: String?,
        idCargo: String?,
        idUnit: String?,
        amount: Double?,
        pricePerUnit: Currency?
    ) {

        val connection = ConnectService(stateResponse)
        connection.makeConnection<String>(
            AddNewCargoToDocument(
                idDocument,
                idCargo,
                idUnit,
                amount,
                pricePerUnit
            )
        )


    }


    fun removeCargo(documentPosition: DocumentPosition, deleteAll: Boolean) {

        /*if (deleteAll || documentPosition.amount <= 1)
            _scannedCargo.value = _scannedCargo.value?.minus(documentPosition)
        else
            documentPosition.amount -= 1
        _scannedCargoBeforeFilter.postValue(_scannedCargo.value)*/

    }


    @OptIn(FlowPreview::class)
    suspend fun filtrDocumentPosition(chars: String): MutableList<DocumentPosition>? {
        //Important! : Function toList() make a copy od list

        if (chars.isNotEmpty())
            return (_scannedCargoCopy.filter {
                it.cargo?.name?.contains(
                    chars,
                    ignoreCase = true
                )!!
            }).toMutableList()
        else
            return _scannedCargoCopy


    }

    suspend fun getDocumentDefinitions() {
        val gson = Gson()
        val connection = ConnectService(stateResponse)
        var receiveList = connection.makeConnection<List<DocumentDefinition>>(
            GetDocumentDefinitions()
        )
        if (receiveList != null) {
            val convertedList = gson.fromJson<List<DocumentDefinition>>(gson.toJson(receiveList))
            _documentDefinitions.postValue((convertedList))
        }


    }


    suspend fun getDocumentsInTemp() {
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

    suspend fun getDocumentPositions(idDocument: String): MutableList<DocumentPosition> {
        _scannedCargoCopy.clear()
        val gson = Gson()
        val connection = ConnectService(stateResponse)
        var convertedList = mutableListOf<DocumentPosition>()
        var receiveList = connection.makeConnection<List<DocumentPosition>>(
            GetDocumentPositionsOnDocument(idDocument)
        )
        if (receiveList != null) {
            convertedList = gson.fromJson<MutableList<DocumentPosition>>(gson.toJson(receiveList))
        }
        _scannedCargoCopy.addAll(convertedList)
        return convertedList

    }

    suspend fun getCargoInformationByEan(ean: String): Cargo? {
        if (ean != null) {
            val gson = Gson()
            val connection = ConnectService(stateResponse)
            var receiveList = connection.makeConnection<Any>(
                GetCargoByEAN(ean)
            )
            val cargo = gson.fromJson<Cargo>(gson.toJson(receiveList))
            return cargo;
        }

        return null

    }

    suspend fun createNewDocument(): Document {
        val gson = Gson()
        val connection = ConnectService(stateResponse)
        var result = connection.makeConnection<Any>(
            CreateNewDocument(_document.value)
        )
        val document = gson.fromJson<Document>(gson.toJson(result))
        return document


    }

    suspend fun getContractors() {
        _contractors.postValue(emptyList())
        val gson = Gson()
        val connection = ConnectService(stateResponse)
        var receiveList = connection.makeConnection<List<DocumentPosition>>(
            GetContractors()
        )
        if (receiveList != null) {
            val convertedList = gson.fromJson<List<Contractor>>(gson.toJson(receiveList))
            _contractors.postValue(convertedList)


        }
    }

    suspend fun confirmDocument(idDocument: String) {

        val connection = ConnectService(stateResponse)
        connection.makeConnection<Boolean>(ConfirmDocument(idDocument))


    }

    fun updateDocument(document: Document) {
        // Zaktualizuj wartość obiektu w wątku głównym
        viewModelScope.launch {
            _document.value = document
        }
    }


}




