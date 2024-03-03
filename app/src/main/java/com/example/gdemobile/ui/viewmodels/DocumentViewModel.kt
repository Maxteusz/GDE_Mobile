package com.example.gdemobile.ui.viewmodels

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.document.DocumentDao
import com.example.gdemobile.helpers.documenttypes.IDocumentType
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.launch

class DocumentViewModel: ViewModel(), IViewModel, IViewModelList {
    override var stateResponse: IStateResponse? = null
    override var recyclerViewScrollState: Parcelable? = null
    private var _documents= MutableLiveData<List<Document>>(emptyList())
    val documents: LiveData<List<Document>>
        get() = _documents
     fun getDocuments(documentType : IDocumentType) {
         viewModelScope.launch {
             _documents.postValue(stateResponse?.let {
                 DocumentDao(it).getDocumentsByType(
                     documentType = documentType.subType?.symbol!!
                 )
             })
         }
    }

    suspend fun createDocument(document : Document)
    {
        stateResponse?.let { DocumentDao(it).createDocument(document) }
    }

    suspend fun confirmDocument(document : Document)
    {
        stateResponse?.let { DocumentDao(it).confirmDocument(document.id) }
    }



}