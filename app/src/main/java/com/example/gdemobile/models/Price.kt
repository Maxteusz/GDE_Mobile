package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class Price {
    @SerializedName("Id")
    val id : Int = 0;
    @SerializedName("Nazwa")
    val name = ""
    @SerializedName("CenaBruttoZaIlosc")
    val bruttoPerAmount : Currency = Currency()
    @SerializedName("CenaNettoZaIlosc")
    val nettoPerAmount : Currency = Currency()

    object PriceNames
    {
        val PRIMARY = "Podstawowa"
    }
}

