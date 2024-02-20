package com.example.gdemobile.apiConnect.enovaConnect.daos.documentposition


import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao


import com.example.gdemobile.apiConnect.enovaConnect.methods.documentpositions.AddDocumentPosition
import com.example.gdemobile.apiConnect.enovaConnect.methods.documentpositions.GetDocumentPositions
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.IStateResponse

class DocumentPositionDao(stateResponse: IStateResponse?) : Dao(
    stateResponse
), IDocumentPositionDao {

    override suspend fun addDocumentPosition(documentPosition: DocumentPosition,document: Document) {
        request<Unit>(AddDocumentPosition(documentPosition,document))
    }

    override suspend fun getDocumentPositions(idDocument: Int): List<DocumentPosition>? {
        return request<List<DocumentPosition>>(GetDocumentPositions(idDocument))
    }

    override suspend fun deleteDocumentPosition(idDocumentPosition: Int) {
      // request<Unit>(DeleteDocumentPositions(idDocumentPosition))
    }
}