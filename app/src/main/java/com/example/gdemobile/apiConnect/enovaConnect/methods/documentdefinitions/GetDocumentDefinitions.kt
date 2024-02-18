package com.example.gdemobile.apiConnect.enovaConnect.methods.documentdefinitions

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class GetDocumentDefinitions : IConnectEnovaMethod {
    override val methodName: String
        get() = "PobierzDefinicjeDokumenowPrzyjeciaMagazynowegoZewnetrznego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto = Dto(0,20)

    private class Dto(
        @SerializedName("Pomin") val skip: Int,
        @SerializedName("Pobierz") val take: Int)
        : IDto
}