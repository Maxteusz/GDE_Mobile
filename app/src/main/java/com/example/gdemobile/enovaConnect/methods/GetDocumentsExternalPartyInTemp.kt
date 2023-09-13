package com.example.gdemobile.enovaConnect.methods

import android.util.Log
import com.example.gdemobile.enovaConnect.IDto
import com.example.gdemobile.enovaConnect.RequestDto
import com.google.gson.annotations.SerializedName

class GetDocumentsExternalPartyInTemp : IConnectEnovaMethod {

    override val methodName: String = "PobierzDokumentyPrzyjeciaZewnetrznegoMagazynowegoWBuforze"
    override val methodService: String = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto()

     private class Dto : IDto {

        val paginationDto = PaginationDto(0,4)
        class PaginationDto(
            @SerializedName("Pomin") val skip: Int,
            @SerializedName("Pobierz") val take: Int
        )
    }






}