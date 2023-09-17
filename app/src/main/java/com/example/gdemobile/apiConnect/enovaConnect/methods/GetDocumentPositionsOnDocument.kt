package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.BaseDto
import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.google.gson.annotations.SerializedName

class GetDocumentPositionsOnDocument(val idDocument: String) :
    IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzPozycjeDokumentuPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto(idDocument)

    private class Dto(@SerializedName("IdDokumentu") val idDocument: String) : BaseDto(), IDto

}