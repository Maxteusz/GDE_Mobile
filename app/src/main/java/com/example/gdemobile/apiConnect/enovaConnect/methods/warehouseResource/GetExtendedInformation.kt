package com.example.gdemobile.apiConnect.enovaConnect.methods.warehouseResource

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetExtendedInformation( val idCargo : Int, val idWarehouse : Int) : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzRozszeZasobyWgTowaru"
    override val methodService: String
        get() = "APIEnova.Services.IZasobMagazynowyService, APIEnova"
    override val dto: IDto
        get() = Dto(idCargo,idWarehouse)

    private class Dto(
        @SerializedName("idTowaru") val idCargo : Int,
        @SerializedName("idMagazynu") val idWarehouse: Int) :  IDto
}