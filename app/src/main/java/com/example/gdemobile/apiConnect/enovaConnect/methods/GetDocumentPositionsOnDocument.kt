package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.BaseDto
import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.google.gson.annotations.SerializedName

class GetDocumentPositionsOnDocument(val idDocument: String = "1c157a9e-9b1b-435b-b7c8-e3a90e3e1919") :
    IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzPozycjeDokumentuPrzyjeciaWewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto(idDocument)

    private class Dto(@SerializedName("IdDokumentu") val idDocument: String) : BaseDto(), IDto

}