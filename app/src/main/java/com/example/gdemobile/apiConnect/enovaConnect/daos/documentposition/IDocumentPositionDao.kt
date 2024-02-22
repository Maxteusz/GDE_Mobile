package com.example.gdemobile.apiConnect.enovaConnect.daos.documentposition

import com.example.gdemobile.models.DocumentPosition

interface IDocumentPositionDao {
    suspend fun addDocumentPosition(documentPosition: DocumentPosition, documentID : Int)
    suspend fun getDocumentPositions(idDocument: Int) : List<DocumentPosition>?
    suspend fun deleteDocumentPosition(idDocumentPosition : Int)
}