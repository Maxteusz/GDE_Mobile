package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.google.gson.annotations.SerializedName
import java.time.temporal.TemporalAmount
import java.util.Currency


//TODO(Adjust later to get cargo by EAN )
class AddNewCargoToDocument(
   val idDocument: String,
   val idCargo: String,
    val idUnit: String,
    val amount: Double,
    val price: Currency
) : IConnectEnovaMethod {
    override val methodName: String
        get() = "DodajPozycjeDokumentuPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = DTO(idDocument,idCargo,idUnit,amount, price)

   private class DTO(
       @SerializedName("IdDokumentu") val idDocument: String,
       @SerializedName("IdTowaru") val idCargo: String,
       @SerializedName("IdJednostki") val idUnit: String,
       @SerializedName("Ilosc") val amount: Double,
       @SerializedName("Cena") val price: Currency


    ) : IDto
}