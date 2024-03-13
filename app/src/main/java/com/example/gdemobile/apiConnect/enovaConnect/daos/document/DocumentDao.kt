package com.example.gdemobile.apiConnect.enovaConnect.daos.document

import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.document.ConfirmDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.document.CreateNewDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.document.GetDocumentsByCategory
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import com.google.firebase.inject.Deferred

class DocumentDao(stateResponse: IStateResponse)  :
    IDocumentDao,
    Dao(stateResponse) {

    override suspend fun confirmDocument(idDocument : Int) {
        requestObject<Unit>(ConfirmDocument(idDocument))
    }


    override suspend fun createDocument(document: Document): Document? {
        return requestObject<Document>(CreateNewDocument(document))
    }

    override suspend fun getDocumentsByType(documentType: String): List<Document>? {
        return requestList<Document>(GetDocumentsByCategory(documentType))
    }






}