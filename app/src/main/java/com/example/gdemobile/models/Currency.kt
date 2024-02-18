package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class Currency(

    @SerializedName("Wartosc")
    val value : Double = 0.0,
    @SerializedName("Symbol")
    val symbol : String = "PLN"
) {

    companion object {
        val symbols = listOf("PLN", "EUR");
    }

    override fun toString(): String {
        return "$value $symbol"
    }


}


