package com.example.gdemobile.apiConnect.enovaConnect.methods.warehouse

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto

class GetWarehouses : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzMagazyny"
    override val methodService: String
        get() = "APIEnova.Services.IMagazynService, APIEnova"
    override val dto: IDto
        get() = Dto()

    private class Dto : IDto {}
}