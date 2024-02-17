package com.example.gdemobile.apiConnect.enovaConnect.daos

import com.example.gdemobile.apiConnect.enovaConnect.ConnectService
import com.example.gdemobile.apiConnect.enovaConnect.methods.IConnectEnovaMethod
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.utils.ExtensionFunction.Companion.fromJson
import com.google.gson.Gson
import java.lang.Exception
import java.net.ConnectException


abstract class Dao(val stateResponse: IStateResponse) {
    suspend inline fun <reified T>request(method : IConnectEnovaMethod): T? {
        try {
            val gson = Gson()
            val connection = ConnectService(stateResponse)
            var receiveDto = connection.makeConnection<List<Document>>(method)
            return gson.fromJson<T>(gson.toJson(receiveDto))
        } catch (e: Exception)
        {
            stateResponse.OnError("Błąd pobierania danych")
            return null
        }
    }
}