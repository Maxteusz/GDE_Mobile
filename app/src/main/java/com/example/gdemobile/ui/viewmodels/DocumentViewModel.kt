package com.example.gdemobile.ui.viewmodels

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.document.DocumentDao
import com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes.IActionType
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.launch

class DocumentViewModel : ViewModel(), IViewModel, IViewModelList {
    override var stateResponse: IStateResponse? = null
    override var recyclerViewScrollState: Parcelable? = null
    private var _documents = MutableLiveData<List<Document>?>(emptyList())
    private var _originalDocumentList = MutableLiveData<List<Document>?>(emptyList())


    val documents: MutableLiveData<List<Document>?>
        get() = _documents

    fun getDocuments(documentType: IActionType) {
        _documents.postValue(emptyList())
        viewModelScope.launch {
            val documentList = DocumentDao(stateResponse!!).getDocumentsByType(
                documentType = documentType.subType?.symbol!!
            )?.sortedByDescending { a -> a.id }
            _documents.postValue(documentList)
            _originalDocumentList.postValue(documentList)

        }
    }

    fun filterDocuments(text: String) {
        if (text.isEmpty()) {
            _documents.postValue(_originalDocumentList.value)
        }
        val filteredList = _originalDocumentList.value?.filter {
            it.number.contains(text, ignoreCase = true)
                    || it.describe.contains(text, ignoreCase = true)
                    || it.contractor?.code?.contains(text, ignoreCase = true) == true
                    || it.contractor?.code?.contains(text, ignoreCase = true) == true
        }
        _documents.postValue(filteredList)
    }

    suspend fun createDocument(document: Document): Document? {
        return stateResponse?.let { DocumentDao(it).createDocument(document) }
    }


    suspend fun confirmDocument(document: Document) =
        stateResponse?.let { DocumentDao(it).confirmDocument(document.id) }

    suspend fun deleteDocument(document: Document) =
        stateResponse?.let { DocumentDao(it).deleteDocument(document.id) }
}





