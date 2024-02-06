package com.example.gdemobile.apiConnect.enovaConnect.repositories

import com.example.gdemobile.apiConnect.enovaConnect.ConnectService
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetContractors
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.utils.ExtensionFunction.Companion.fromJson
import com.google.gson.Gson

class RepositoryContractor(val stateResponse: IStateResponse?) {
    suspend fun getContractors(): List<Contractor> {
        val gson = Gson()
        val connection = stateResponse?.let { ConnectService(it) }
        var receiveList = connection?.makeConnection<List<DocumentPosition>>(
            GetContractors())
        return gson.fromJson<List<Contractor>>(gson.toJson(receiveList))
    }

}