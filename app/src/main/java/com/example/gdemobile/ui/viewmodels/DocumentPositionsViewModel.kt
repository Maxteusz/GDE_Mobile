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


class DocumentPositionsViewModel(override var stateResponse: IStateResponse? = null) : ViewModel(),
    IViewModel, IViewModelList {
    override var recyclerViewScrollState: Parcelable? = null
    private var _originalDocumentPositions = MutableLiveData<List<DocumentPosition>>(emptyList())
    private var _documentPositions = MutableLiveData<List<DocumentPosition>>(emptyList())
    val documentPositions: MutableLiveData<List<DocumentPosition>>
        get() = _documentPositions

    fun getDocumentPositions(document: Document) {
        _originalDocumentPositions.value = emptyList()
        viewModelScope.launch {
            val positions = DocumentPositionDao(stateResponse)
                .getDocumentPositions(document.id)
                ?.sortedByDescending { a -> a.id }
            _originalDocumentPositions.postValue(positions ?: emptyList())
            _documentPositions.postValue(positions ?: emptyList())

        }
    }

    fun addDocumentPosition(documentPosition: DocumentPosition, documentID: Int) {
        viewModelScope.launch {
            DocumentPositionDao(stateResponse)
                .addDocumentPosition(documentPosition, documentID)
        }
    }

    fun filterDocumentPosition(phrase: String) {
        if (phrase.isEmpty())
            _documentPositions.postValue(_originalDocumentPositions.value)

        _documentPositions
            .postValue(_originalDocumentPositions.value
                ?.filter { a ->
                    a.cargo?.code?.contains(phrase, ignoreCase = true) == true ||
                            a.cargo?.name?.contains(phrase, ignoreCase = true) == true
                })

    }
    suspend fun deleteDocumentPosition(idDocumentPosition: Int) : Int?
    {
        return DocumentPositionDao(stateResponse).deleteDocumentPosition(idDocumentPosition)
    }


}