package com.example.gdemobile.enovaConnect.methods

import android.util.Log
import com.example.gdemobile.enovaConnect.IDto
import com.example.gdemobile.enovaConnect.RequestDto
import com.google.gson.annotations.SerializedName

class GetDocumentsExternalPartyInTemp : IConnectEnovaMethod {

    override val methodName: String = "PobierzDokumentyPrzyjeciaZewnetrznegoMagazynowegoWBuforze"
    override val methodService: String = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override fun  getBody(): RequestDto {
        val body = RequestDto(this,Dto())

        Log.i("Bodyy", body.toString())
        return body;

    }
     private class Dto : IDto {

        val paginationDto = PaginationDto(0,4)
        class PaginationDto(
            @SerializedName("Pomin") val skip: Int,
            @SerializedName("Pobierz") val take: Int
        )
    }






}