package com.example.gdemobile.apiConnect.enovaConnect.methods.documentPositions

import com.example.gdemobile.apiConnect.enovaConnect.BaseDto
import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.example.gdemobile.apiConnect.enovaConnect.methods.IConnectEnovaMethod
import com.google.gson.annotations.SerializedName

class GetDocumentPositions(val idDocument: Int) :
    IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzPozycjeDokumentuPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto(idDocument)

    private class Dto(@SerializedName("IdDokumentu") val idDocument: Int) : BaseDto(), IDto

}