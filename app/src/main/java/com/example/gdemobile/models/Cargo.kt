package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

open class Cargo(
    @SerializedName("IdTowaru")
    val id: String,
    @SerializedName("KodTowaru")
    val code: String,
    @SerializedName("Nazwa")
    val name: String,
    @SerializedName("KodEan")
    val barcode: String
) {
    val unit = Unit()


    class Unit()
    {
        val id : String = ""
        val symbol = ""
    }
}