package com.example.gdemobile.config.updateApp

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto

class UpdateAppConnectionInformation : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzPlikApk"
    override val methodService: String
        get() = "APIEnova.Services.IAktualizacjaAppServicecs, APIEnova"
    override val dto: IDto = Dto()

    private class Dto : IDto


}
