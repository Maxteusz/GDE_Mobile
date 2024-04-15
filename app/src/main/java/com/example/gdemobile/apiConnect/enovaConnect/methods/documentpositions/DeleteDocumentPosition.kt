package com.example.gdemobile.apiConnect.enovaConnect.methods.documentpositions

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class DeleteDocumentPosition(val idDocumentPosition: Int) : IConnectEnovaMethod {
    override val methodName: String
        get() = "UsunPozycje"
    override val methodService: String
        get() = "APIEnova.Services.IPozycjeDokumentuService, APIEnova"
    override val dto: IDto
        get() = Dto(idDocumentPosition)

    private class Dto(@SerializedName("idPozycji") val idDocumentPosition: Int) : IDto
}