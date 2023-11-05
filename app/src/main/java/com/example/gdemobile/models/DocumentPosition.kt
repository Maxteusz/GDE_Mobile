package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class DocumentPosition(
    @SerializedName("Id")
   val  id: Int,
    @SerializedName("Ilosc")
    var amount: Double,
    @SerializedName("Cena jednostkowa")
    var valuePerUnit: Currency
) {

    @SerializedName("Towar")
    val cargo : Cargo? = null
    @SerializedName("Jednostka")
    val unit : Cargo.Unit? = null

    fun getAmountValue () = amount.toString()  + " " + unit?.name




}