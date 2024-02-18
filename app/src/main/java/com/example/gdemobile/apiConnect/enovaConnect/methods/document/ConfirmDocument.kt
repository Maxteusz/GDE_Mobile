package com.example.gdemobile.apiConnect.enovaConnect.methods.document

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class ConfirmDocument(val idDocument: Int) : IConnectEnovaMethod {
    override val methodName: String
        get() ="ZatwierdzDokumentPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto(idDocument)

    private class Dto (@SerializedName("IdDokumentu") val idDocument : Int) : IDto
}