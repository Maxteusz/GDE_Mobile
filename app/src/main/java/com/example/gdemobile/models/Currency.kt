package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class Currency(
    @SerializedName("Wartosc")
    val value : Double = 0.0,
    @SerializedName("Symbol")
    val symbol : String = ""

) {
    object Symbol
    {
        val pln = "PLN"
        val eur = "EUR"
    }
}


