package com.example.gdemobile.apiConnect.enovaConnect.methods.cargo

import com.example.gdemobile.apiConnect.enovaConnect.BaseDto
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetCargoInformation(val cargoId : String) : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzInfoTowaru"
    override val methodService: String
        get() = "GdeApi.ITowaryService, GdeApi"
    override val dto: IDto
        get() = Dto(cargoId)
    private class Dto(@SerializedName("IdTowaru") val idCargo: String) :  IDto
}