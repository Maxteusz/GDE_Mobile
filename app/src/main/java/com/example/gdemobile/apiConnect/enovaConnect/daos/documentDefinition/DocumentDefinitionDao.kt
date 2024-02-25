package com.example.gdemobile.apiConnect.enovaConnect.daos.documentDefinition

import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.documentdefinitions.GetDocumentDefinitions
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.ui.IStateResponse

class DocumentDefinitionDao(stateResponse: IStateResponse?) : IDocumentDefinitionDao,
    Dao(stateResponse) {
    override suspend fun getdDcumentDefinitions(documentType: String): List<DocumentDefinition>? {
        return requestList<DocumentDefinition>(GetDocumentDefinitions(documentType))
    }

}