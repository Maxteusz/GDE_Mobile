package com.example.gdemobile.apiConnect.enovaConnect.daos.document

import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.document.ConfirmDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.document.CreateNewDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.document.GetDocumentsByCategory
import com.example.gdemobile.helpers.DocumentType
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse

class DocumentDao(stateResponse: IStateResponse)  :
    IDocumentDao,
    Dao(stateResponse) {

    override suspend fun confirmDocument(idDocument : Int) {
        request<Unit>(ConfirmDocument(idDocument))
    }


    override suspend fun createDocument(document: Document): Document?{
        return request<Document>(CreateNewDocument(document))
    }

    override suspend fun getDocumentsByType(documentType: String): List<Document>? {
        return request<List<Document>>(GetDocumentsByCategory(documentType))
    }






}