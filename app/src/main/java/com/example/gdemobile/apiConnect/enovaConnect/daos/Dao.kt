package com.example.gdemobile.apiConnect.enovaConnect.daos

import android.util.Log
import com.example.gdemobile.apiConnect.enovaConnect.ConnectService
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.utils.ExtensionFunction.Companion.fromJson
import com.google.gson.Gson


abstract class Dao(val stateResponse: IStateResponse?) {
    suspend inline fun <reified T>requestObject(method : IConnectEnovaMethod): T? {
        try {
            val gson = Gson()
            val connection = ConnectService(stateResponse)
            var receiveDto = connection.makeConnection<Any>(method)
            return gson.fromJson<T>(gson.toJson(receiveDto))
        } catch (e: Exception)
        {
            Log.i("DaoRequestException", e.message.toString())
            stateResponse?.OnError("Błąd pobierania danych")
            return null
        }
    }

    suspend inline fun <reified T>requestList(method : IConnectEnovaMethod): List<T>? {
        try {
            val gson = Gson()
            val connection = ConnectService(stateResponse)
            var receiveDto = connection.makeConnection<Any>(method)
            if(receiveDto == null)
                return emptyList()
            return gson.fromJson<List<T>>(gson.toJson(receiveDto))
        } catch (e: Exception)
        {
            Log.i("DaoRequestException", e.message.toString())
            stateResponse?.OnError("Błąd pobierania danych")
            return emptyList<T>()
        }
    }




}