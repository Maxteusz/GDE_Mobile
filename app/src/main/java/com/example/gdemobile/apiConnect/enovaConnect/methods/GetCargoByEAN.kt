package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.BaseDto
import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.google.gson.annotations.SerializedName

class GetCargoByEAN(private val ean : String) : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzInfoTowaruPoKodzieEan"
    override val methodService: String
        get() = "GdeApi.ITowaryService, GdeApi"
    override val dto: IDto
        get() = Dto(ean)

    private class Dto(@SerializedName("KodEan") val ean: String) : BaseDto(), IDto
}