package com.example.gdemobile.apiConnect.enovaConnect.methods.warehouseResource

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetPrimaryInformation(val barcode : String) : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzPodstZasobyWgTowaru"
    override val methodService: String
        get() = "APIEnova.Services.IMagazynService, APIEnova"
    override val dto: IDto
        get() = Dto(barcode)

    private class Dto(
        @SerializedName("barcode") val ean: String) :  IDto
}