package com.example.gdemobile.apiConnect.enovaConnect.methods.document

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetDocumentsByCategory(val documentType : String) : IConnectEnovaMethod {


    override val methodName: String = "PobierzDokumentyHandloweWgKategorii"
    override val methodService: String = "APIEnova.Services.IDokumentHandlowyService, APIEnova"
    override val dto: IDto
        get() = Dto(documentType)

      private class Dto(@SerializedName("dto") val documentType : String) :  IDto
}