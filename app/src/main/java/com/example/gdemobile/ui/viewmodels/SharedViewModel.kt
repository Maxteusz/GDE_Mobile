package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdemobile.helpers.documenttypes.IDocumentType
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentPosition

class SharedViewModel : ViewModel() {
    private var _document = MutableLiveData<Document>()
    var document: LiveData<Document> = _document
    lateinit var documentType: IDocumentType


    private var _documentPosition = MutableLiveData(DocumentPosition())
    var documentPosition: LiveData<DocumentPosition> = _documentPosition

    //ScanBarcodeFragment
    //Require to unblock/block scanning after close amount (in depends on conf)
    private var _lockScanning = MutableLiveData(false)
    var lockScanning: LiveData<Boolean> = _lockScanning

    private var _blockingLoadData = MutableLiveData(false)
    var blockingLoadData: LiveData<Boolean> = _blockingLoadData

    fun setBlockLoadData(value: Boolean) {
        _blockingLoadData.value = value
    }

    fun getBlockLoadData() = _blockingLoadData.value

    fun setDocument(document: Document) {
        _document.value = document
    }

    fun setDocumentPosition(documentPosition: DocumentPosition) {
        _documentPosition.value = documentPosition
    }

    fun lockScanning() = run { _lockScanning.value = true }
    fun unlockScanning() = run { _lockScanning.value = false }

}