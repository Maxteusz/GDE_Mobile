package com.example.gdemobile.apiConnect.enovaConnect.daos.cargo

import com.example.gdemobile.apiConnect.enovaConnect.ConnectService
import com.example.gdemobile.apiConnect.enovaConnect.methods.GetCargoByEAN
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.utils.ExtensionFunction.Companion.fromJson
import com.google.gson.Gson

class CargoDao(val stateResponse: IStateResponse?) {

    suspend fun getCargoInformationByEan(ean: String): Cargo? {
            val gson = Gson()
            val connection = stateResponse?.let { ConnectService(it) }
            var receiveList = connection?.makeConnection<Any>(
                GetCargoByEAN(ean)
            )
            return gson.fromJson<Cargo>(gson.toJson(receiveList))
    }
}