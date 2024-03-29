package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class Quantity {
    @SerializedName("Symbol")
    var symbol : String = "N/n"
    @SerializedName("Ilosc")
    var value : Double = 0.0

    override fun toString(): String {
        return "$value $symbol"
    }
    fun getValueToString() : String = value.toString()
}