package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class DocumentPosition(
    id: String,
    code: String,
    name: String,
    unit: String,
    barcode: String,
    @SerializedName("Ilosc")
    var amount: Double,
    @SerializedName("Cena jednostkowa")
    var value: Currency
) : Cargo(id, code, name, barcode, unit) {

    val fullName: String
        get() {
            if (name.isNullOrEmpty())
                return "$code"
            else
                return "$code - $name"
        }


}