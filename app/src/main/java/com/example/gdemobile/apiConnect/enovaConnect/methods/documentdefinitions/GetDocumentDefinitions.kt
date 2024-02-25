package com.example.gdemobile.apiConnect.enovaConnect.methods.documentdefinitions

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetDocumentDefinitions(val documentType : String) : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzDefinicjeDokumentuWgKategorii"
    override val methodService: String
        get() = "APIEnova.Services.IDefDokumentowService, APIEnova"
    override val dto: IDto = Dto(documentType)

    private class Dto(
        @SerializedName("typDokumentu") val documentType : String)
        : IDto
}