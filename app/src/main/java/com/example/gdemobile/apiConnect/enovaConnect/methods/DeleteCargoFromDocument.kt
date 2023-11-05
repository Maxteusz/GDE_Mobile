package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.BaseDto
import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.google.gson.annotations.SerializedName

class DeleteCargoFromDocument(val idDocumentPosition: Int) : IConnectEnovaMethod {
    override val methodName: String
        get() = "UsunPozycjeDokumentuPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto(idDocumentPosition)

    private class Dto(@SerializedName("IdPozycji") val idDocumentPosition: Int) : BaseDto(), IDto
}