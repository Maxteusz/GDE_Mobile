package com.example.gdemobile.apiConnect.enovaConnect.methods.warehouseResource

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetExtendedInformation( val barcode : String, val IDWarehouse : Int) : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzRozszeZasobyWgTowaru"
    override val methodService: String
        get() = "APIEnova.Services.IMagazynService, APIEnova"
    override val dto: IDto
        get() = Dto(barcode,IDWarehouse)

    private class Dto(
        @SerializedName("barcode") val ean: String,
        @SerializedName("IDMagazynu") val IDWarehouse: Int) :  IDto
}