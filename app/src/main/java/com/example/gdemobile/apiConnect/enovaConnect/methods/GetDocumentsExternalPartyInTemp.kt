package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.google.gson.annotations.SerializedName

class GetDocumentsExternalPartyInTemp : IConnectEnovaMethod {

    override val methodName: String = "PobierzDokumentyPrzyjeciaZewnetrznegoMagazynowegoWBuforze"
    override val methodService: String = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto()

     private class Dto : IDto {
         @SerializedName("Paginacja")
        val paginationDto = PaginationDto(0,4)
        class PaginationDto(
            @SerializedName("Pomin") val skip: Int,
            @SerializedName("Pobierz") val take: Int
        )
    }






}