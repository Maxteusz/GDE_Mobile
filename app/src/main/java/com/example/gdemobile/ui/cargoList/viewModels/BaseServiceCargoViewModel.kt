package com.example.gdemobile.ui.cargoList


import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdemobile.apiConnect.enovaConnect.daos.cargo.CargoDao
import com.example.gdemobile.apiConnect.enovaConnect.daos.contractor.ContractorDao
import com.example.gdemobile.apiConnect.enovaConnect.daos.document.DocumentDao
import com.example.gdemobile.helpers.DocumentType
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.viewModels.IShowAmountDialog


open class BaseServiceCargoViewModel : ViewModel(), IShowAmountDialog {


     var stateResponse: IStateResponse? = null

    private var _document = MutableLiveData<Document>()
    private val _scannedCargoCopy = mutableListOf<DocumentPosition>()
    private var _contractors = MutableLiveData<List<Contractor>?>(emptyList())
    private var _documentListInTemp = MutableLiveData<List<Document>>(emptyList())
    private var _documentDefinitions = MutableLiveData<List<DocumentDefinition>?>(emptyList())

    var isRequiredLoadData = MutableLiveData<Boolean>(true)
    val scannedBarcode: MutableLiveData<String> = MutableLiveData("")
    val recyclerViewScrollState: MutableLiveData<Parcelable?> = MutableLiveData()
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

    fun filtrDocumentPosition(chars: String): MutableList<DocumentPosition>? {
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
       /* val gson = Gson()
        val connection = stateResponse?.let { ConnectService(it) }
        var receiveList = connection?.makeConnection<List<DocumentDefinition>>(
            GetDocumentDefinitions()
        )
        if (receiveList != null) {
            val convertedList = gson.fromJson<List<DocumentDefinition>>(gson.toJson(receiveList))
            _documentDefinitions.postValue((convertedList))
        }*/


    }


    suspend fun getDocumentsInTemp() {
        _documentListInTemp.postValue(stateResponse?.let { DocumentDao(it).getDocumentsByType(documentType = DocumentType.PWPW) })
    }




    suspend fun getCargoInformationByEan(ean: String): Cargo? {
        return CargoDao(stateResponse).getCargoInformationByEan(ean)

    }

    suspend fun createNewDocument(document: Document): Document {
        return Document()
    }

    suspend fun getContractors() {
        _contractors.postValue(emptyList())
        _contractors.postValue(ContractorDao(stateResponse).getContractors())
    }


    suspend fun confirmDocument(idDocument: String) {
        "DocumentDao(stateResponse).confirmDocument(idDocument = idDocument)"
    }


    suspend fun deleteCagoFromDocument(idDocumentPosition: Int) {
        /*stateResponse?.let {
            ConnectService(it)
                .makeConnection<Any>(
                    DeleteDocumentPositions(idDocumentPosition)
                )
        }*/
    }


    suspend fun addCargoOnDocument(){}
       /* fragment: Fragment,
        documentPosition: DocumentPosition,
        idDocument: String,
        fastAddingStateResponse: IStateResponse
    ) {
        if (Config.fastAddingDocumentPosition) {
            stateResponse = fastAddingStateResponse
            addCargoOnDocument(
                idDocument,
                documentPosition
            )
        } else
            showAmountDialog(fragment, idDocument, documentPosition)*/
    }

    suspend fun addCargoOnDocument() {}
      /*  idDocument: String?,
        documentPosition: DocumentPosition?
    ) {

        if (Config.fastAddingDocumentPosition) {
            documentPosition?.amount = 1.0
            documentPosition?.valuePerUnit =
                documentPosition?.cargo?.prices?.first { it.name == PriceNames.PRIMARY }?.bruttoPerAmount!!
            documentPosition.unit = documentPosition.cargo?.mainUnit
        }
        val connection = stateResponse?.let { ConnectService(it) }
        connection?.makeConnection<String>(
            AddDocumentPosition(
                idDocument,
                documentPosition?.cargo?.id,
                documentPosition?.unit?.id,
                documentPosition?.amount,
                documentPosition?.valuePerUnit
            )

        )*/


    suspend fun refreshData() {}
     /*   document.value?.documentPositions =
            getDocumentPositions(document.value?.id!!)
        _document.postValue(_document.value)*/






