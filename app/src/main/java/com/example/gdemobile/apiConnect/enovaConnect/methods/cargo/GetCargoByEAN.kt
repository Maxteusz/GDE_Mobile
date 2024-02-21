package com.example.gdemobile.apiConnect.enovaConnect.methods.cargo

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetCargoByEAN(private val ean : String) : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzTowar"
    override val methodService: String
        get() = "APIEnova.Services.ITowarService, APIEnova"
    override val dto: IDto
        get() = Dto(ean)

    private class Dto(@SerializedName("dto") val ean: String) :  IDto
}