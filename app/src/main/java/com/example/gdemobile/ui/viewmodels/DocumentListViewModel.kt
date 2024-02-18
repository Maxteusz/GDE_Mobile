package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdemobile.helpers.DocumentType
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse

class DocumentListViewModel: ViewModel(), IViewModel {
    override var stateResponse: IStateResponse? = null
    override val documentType: DocumentType? = null
    private var _documents= MutableLiveData<List<Document>>(emptyList())
    val documents: LiveData<List<Document>>
        get() = _documents


}