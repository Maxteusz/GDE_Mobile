package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DocumentPosition (
    @SerializedName("Ilosc")
    var amount: Double,
    @SerializedName("CenaJednostkowa")
    var valuePerUnit: Currency
) : Serializable {
    @SerializedName("Id")
    val  id: Int = 0
    @SerializedName("Towar")
    val cargo : Cargo? = null
    @SerializedName("Jednostka")
    val unit : Cargo.Unit? = null

    fun getAmountFullInfo () = amount.toString()  + " " + unit?.name
    fun getAmountValue () = amount.toString()




}