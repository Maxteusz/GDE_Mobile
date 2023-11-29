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
    val additionalUnits = listOf(Unit())
    @SerializedName("Ceny")
    val prices  = listOf(Price())


    class Unit()
    {
        @SerializedName("Id")
        val id : String = ""
        @SerializedName("Nazwa")
        val name = ""
    }
}