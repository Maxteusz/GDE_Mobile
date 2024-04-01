package com.example.gdemobile.apiConnect.enovaConnect.methods.warehouseResource

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetPrimaryInformation(val idCargo: Int) : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzPodstZasobyWgTowaru"
    override val methodService: String
        get() = "APIEnova.Services.IZasobMagazynowyService, APIEnova"
    override val dto: IDto
        get() = Dto(idCargo)

    private class Dto(
        @SerializedName("idTowaru") val idCargo: Int) :  IDto
}