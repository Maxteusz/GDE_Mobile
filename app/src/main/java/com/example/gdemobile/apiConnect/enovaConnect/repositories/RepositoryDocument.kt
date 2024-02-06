package com.example.gdemobile.apiConnect.enovaConnect.repositories

import com.example.gdemobile.apiConnect.enovaConnect.ConnectService
import com.example.gdemobile.apiConnect.enovaConnect.methods.document.ConfirmDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.document.CreateNewDocument
import com.example.gdemobile.apiConnect.enovaConnect.methods.document.GetAllDocuments
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.utils.ExtensionFunction.Companion.fromJson
import com.google.gson.Gson

class RepositoryDocument(var stateResponse: IStateResponse?)  {

    suspend fun getDocumentsInTemp(): List<Document>? {
        val gson = Gson()
        val connection = stateResponse?.let { ConnectService(it) }
        var receiveList = connection?.makeConnection<List<Document>>(
            GetAllDocuments()
        )
        if(receiveList == null)
            return emptyList()
        return gson.fromJson<List<Document>>(gson.toJson(receiveList))
    }

    suspend fun createNewDocument(document: Document): Document {
        val gson = Gson()
        val connection = stateResponse?.let { ConnectService(it) }
        var result = connection?.makeConnection<Any>(
            CreateNewDocument(document)
        )
        return gson.fromJson<Document>(gson.toJson(result))
    }

    suspend fun confirmDocument(idDocument : String) {
        val connection = stateResponse?.let { ConnectService(it) }
        connection?.makeConnection<Boolean>(ConfirmDocument(idDocument))
    }

}