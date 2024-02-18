package com.example.gdemobile.apiConnect.enovaConnect.methods.document

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto

class GetDocumentsByCategory(val documentType : String) : IConnectEnovaMethod {

    //TODO() = Change name method
    override val methodName: String = "PobierzWszystkieDokumentyHandlowe"
    override val methodService: String = "APIEnova.Services.IDokumentHandlowyService, APIEnova"
    override val dto: IDto
        get() = Dto(documentType)

      private class Dto(documentType : String) :  IDto
}