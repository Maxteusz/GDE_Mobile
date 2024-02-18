package com.example.gdemobile.apiConnect.enovaConnect.methods.documentpositions


import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetDocumentPositions(val idDocument: Int) :
    IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzPozycjeDokumentu"
    override val methodService: String
        get() = "APIEnova.Services.IPozycjeDokumentuService, APIEnova"
    override val dto: IDto
        get() = Dto(idDocument)

    private class Dto(@SerializedName("dto") val idDocument: Int) : IDto

}