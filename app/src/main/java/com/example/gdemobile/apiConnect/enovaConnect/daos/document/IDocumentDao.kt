package com.example.gdemobile.apiConnect.enovaConnect.daos.document

import com.example.gdemobile.helpers.DocumentType
import com.example.gdemobile.models.Document

interface IDocumentDao {
   suspend fun createDocument(document : Document) : Document?
    suspend fun getDocumentsByType(documentType : String) : List<Document>?
   suspend fun confirmDocument(idDocument : Int)

}