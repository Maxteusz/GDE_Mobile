package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.google.gson.annotations.SerializedName
import java.time.temporal.TemporalAmount

class AddNewCargoToDocument : IConnectEnovaMethod {
    override val methodName: String
        get() = "DodajPozycjeDokumentuPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = DTO()

   private class DTO(
       @SerializedName("IdDokumentu") val idDocument: String,
       @SerializedName("IdTowaru") val idCargo: String,
       @SerializedName("IdJednostki") val idUnit: String,
       @SerializedName("Ilosc") val amount: Double

    ) : IDto
}