package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.BaseDto
import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.google.gson.annotations.SerializedName

class GetContractors : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzWszystkichKontrahentow"
    override val methodService: String
        get() = "GdeApi.IKontrahenciService, GdeApi"
    override val dto: IDto
        get() = Dto()

    private class Dto : IDto, BaseDto()


}