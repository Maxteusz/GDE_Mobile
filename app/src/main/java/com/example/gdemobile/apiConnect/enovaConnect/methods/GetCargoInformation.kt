package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.BaseDto
import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.google.gson.annotations.SerializedName

class GetCargoInformation(val cargoId : String) :  IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzInfoTowaru"
    override val methodService: String
        get() = "GdeApi.ITowaryService, GdeApi"
    override val dto: IDto
        get() = Dto(cargoId)
    private class Dto(@SerializedName("IdTowaru") val idCargo: String) : BaseDto(), IDto
}