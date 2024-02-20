package com.example.gdemobile.ui.viewmodels

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.documentposition.DocumentPositionDao
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.launch


class DocumentPositionsViewModel() : ViewModel(), IViewModel, IViewModelList {

    override var stateResponse: IStateResponse? = null

    override var recyclerViewScrollState: Parcelable? = null
    private var _documentPositions = MutableLiveData<List<DocumentPosition>>(emptyList())
    val documentPositions: MutableLiveData<List<DocumentPosition>>
        get() = _documentPositions

    fun getDocumentPositions(document: Document) {
        _documentPositions.value = emptyList()
        viewModelScope.launch {
            _documentPositions
                .postValue(
                    DocumentPositionDao(stateResponse)
                        .getDocumentPositions(document.id)
                )}
    }

    fun addDocumentPosition(documentPosition: DocumentPosition, document: Document) {
        viewModelScope.launch {
            DocumentPositionDao(stateResponse)
                .addDocumentPosition(documentPosition,document)
        }
    }


}