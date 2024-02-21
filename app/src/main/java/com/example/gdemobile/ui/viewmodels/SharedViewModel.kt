package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentPosition

class SharedViewModel : ViewModel() {
    private var _document = MutableLiveData<Document>()
    var document : LiveData<Document> = _document

    private var _documentPosition = MutableLiveData<DocumentPosition>()
    var documentPosition : LiveData<DocumentPosition> = _documentPosition

    fun setDocument(document: Document)
    {
        _document.value = document
    }

    fun setDocumentPosition(documentPosition: DocumentPosition)
    {
        _documentPosition.value = documentPosition
    }

}