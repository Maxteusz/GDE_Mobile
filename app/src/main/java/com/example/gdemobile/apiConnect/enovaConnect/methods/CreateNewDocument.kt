package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.example.gdemobile.models.Document
import com.google.gson.annotations.SerializedName

class CreateNewDocument(val document : Document?
) : IConnectEnovaMethod {
    override val methodName: String
        get() = "UtworzNowyDokumentPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto = Dto(
        document?.definition?.id.toString(),
        document?.date!!,
        document.contractor?.id,
        document.describe)

    private class Dto
        (
        @SerializedName("IdDefinicji")
        val idDefinitions: String,
        @SerializedName("DataWystawienia")
        val dateOfIssue: String,
        @SerializedName("IdKontrahenta")
        val idContractor: String?,
        @SerializedName("Opis")
        val descirbe: String?,
    ) : IDto


}


