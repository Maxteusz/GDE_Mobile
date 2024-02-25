package com.example.gdemobile.apiConnect.enovaConnect.methods.contractor

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto

class GetContractors : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzWszystkichKontrahentow"
    override val methodService: String
        get() = "APIEnova.Services.IKontrahentService, APIEnova"
    override val dto: IDto
        get() = Dto()

    private class Dto : IDto


}