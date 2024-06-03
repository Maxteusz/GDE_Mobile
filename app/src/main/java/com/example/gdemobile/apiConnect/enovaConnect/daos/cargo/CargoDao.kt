package com.example.gdemobile.apiConnect.enovaConnect.daos.cargo

import com.example.gdemobile.apiConnect.enovaConnect.ConnectService
import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.cargo.GetCargoByEAN
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.utils.ExtensionFunction.Companion.fromJson
import com.google.gson.Gson

class CargoDao(stateResponse: IStateResponse?) : ICargoDao, Dao(stateResponse) {

    suspend fun getCargoInformationByEan(ean: String): Cargo? {
            return requestObject(GetCargoByEAN(ean))
    }
}