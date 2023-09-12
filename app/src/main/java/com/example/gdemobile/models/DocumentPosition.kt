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
    @SerializedName("CenaJednostkowa")
    var value: Double
) : Cargo(id, code, name, barcode, unit) {

    val fullName: String
        get() {
            if (name.isNullOrEmpty())
                return "$code"
            else
                return "$code - $name"
        }


}