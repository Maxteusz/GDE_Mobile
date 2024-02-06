package com.example.gdemobile.apiConnect.enovaConnect.methods.document

import com.example.gdemobile.apiConnect.enovaConnect.BaseDto
import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.example.gdemobile.apiConnect.enovaConnect.methods.IConnectEnovaMethod

class GetAllDocuments : IConnectEnovaMethod {

    override val methodName: String = "PobierzWszystkieDokumentyHandlowe"
    override val methodService: String = "APIEnova.Services.IDokumentHandlowyService, APIEnova"
    override val dto: IDto
        get() = Dto()

      private class Dto : BaseDto(),IDto
}