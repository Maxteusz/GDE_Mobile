package com.example.gdemobile.apiConnect.enovaConnect.methods.documentpositions

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class DeleteDocumentPositions(val idDocumentPosition: Int) : IConnectEnovaMethod {
    override val methodName: String
        get() = "UsunPozycjeDokumentuPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto(idDocumentPosition)

    private class Dto(@SerializedName("IdPozycji") val idDocumentPosition: Int) : IDto
}