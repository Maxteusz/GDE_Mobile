package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdemobile.models.Document

class SharedViewModel : ViewModel() {
    private var _document = MutableLiveData<Document>()
    var document : LiveData<Document> = _document

    fun setDocument(document: Document)
    {
        _document.value = document
    }

}