package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class DocumentPosition(
    id: String,
    code: String,
    name: String,
    barcode: String,
    @SerializedName("Ilosc")
    var amount: Double,
    @SerializedName("Cena jednostkowa")
    var value: Currency
) : Cargo(id, code, name, barcode) {

    @SerializedName("Towar")
    val cargo : Cargo? = null
    @SerializedName("IdJednostki")
    val idUnit : String? = null
    val fullName: String
        get() {
            if (name.isNullOrEmpty())
                return code
            else
                return "$code - $name"
        }
    fun getAmountValue () = amount.toString()  + " " + getUnit()
    fun getUnit () = cargo?.additionalUnits?.first { it.id == idUnit }?.symbol


}