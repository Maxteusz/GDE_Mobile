package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Cargo(
    @SerializedName("Id")
    val id: String,
    @SerializedName("KodTowaru")
    val code: String,
    @SerializedName("Nazwa")
    val name: String,
    @SerializedName("KodEan")
    val barcode: String
) : Serializable{
    @SerializedName("JednostkaPodstawowa")
    val mainUnit = Unit()
    @SerializedName("DozwoloneJednostki")
    val additionalUnits = listOf(Unit)


    class Unit()
    {
        @SerializedName("IdJednostki")
        val id : String = "00000000-0011-0007-0007-000000000000"
        val symbol = ""
    }
}