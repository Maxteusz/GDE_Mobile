package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.documentDefinition.DocumentDefinitionDao
import com.example.gdemobile.helpers.DocumentType
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.launch

class DocumentDefinitionViewModel() : ViewModel(), IViewModel {

    private var _documentDefinitions= MutableLiveData<List<DocumentDefinition>>(emptyList())
    val documentDefinitions: LiveData<List<DocumentDefinition>>
        get() = _documentDefinitions
    fun getDocumentDefinitions(documentType: String = DocumentType.PWPW)
    {
        viewModelScope.launch {
            _documentDefinitions.postValue(DocumentDefinitionDao(stateResponse).getdDcumentDefinitions(documentType))
        }
    }
    override var stateResponse: IStateResponse? = object  : IStateResponse{
        override fun OnLoading() {

        }

        override suspend fun OnError(message: String) {

        }

        override fun OnSucces() {

        }

    }

}