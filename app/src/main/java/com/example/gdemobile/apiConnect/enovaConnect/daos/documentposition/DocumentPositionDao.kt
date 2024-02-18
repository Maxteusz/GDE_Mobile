package com.example.gdemobile.apiConnect.enovaConnect.daos.DocumentPosition

import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.AddDocumentPosition
import com.example.gdemobile.apiConnect.enovaConnect.methods.documentPositions.DeleteDocumentPositions
import com.example.gdemobile.apiConnect.enovaConnect.methods.documentPositions.GetDocumentPositions
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.IStateResponse

class DocumentPositionDao(stateResponse: IStateResponse?) : Dao(
    stateResponse
), IDOcumentPositionDao{

    override suspend fun addDocumentPosition(documentPosition: DocumentPosition) {
        request<Unit>(AddDocumentPosition(documentPosition))
    }

    override suspend fun getDocumentPositions(idDocument: Int): List<DocumentPosition>? {
        return request<List<DocumentPosition>>(GetDocumentPositions(idDocument))
    }

    override suspend fun deleteDocumentPosition(idDocumentPosition: Int) {
        request<Unit>(DeleteDocumentPositions(idDocumentPosition))
    }
}