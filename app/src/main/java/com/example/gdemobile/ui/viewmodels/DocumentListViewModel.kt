package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.document.DocumentDao
import com.example.gdemobile.helpers.DocumentType
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.launch

class DocumentListViewModel: ViewModel(), IViewModel {
    override var stateResponse: IStateResponse? = null
    override val documentType: DocumentType? = null
    private var _documents= MutableLiveData<List<Document>>(emptyList())
    val documents: LiveData<List<Document>>
        get() = _documents
     fun getDocuments() {
         viewModelScope.launch {
             _documents.postValue(stateResponse?.let {
                 DocumentDao(it).getDocumentsByType(
                     documentType = DocumentType.PWPW
                 )
             })
         }
    }

}