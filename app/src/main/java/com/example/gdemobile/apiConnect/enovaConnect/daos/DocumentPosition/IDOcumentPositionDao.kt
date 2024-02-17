package com.example.gdemobile.apiConnect.enovaConnect.daos.DocumentPosition

import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.models.DocumentPosition

interface IDOcumentPositionDao {
    suspend fun addDocumentPosition(documentPosition: DocumentPosition)
    suspend fun getDocumentPositions(idDocument: Int) : List<DocumentPosition>?
    suspend fun deleteDocumentPosition(idDocumentPosition : Int)
}