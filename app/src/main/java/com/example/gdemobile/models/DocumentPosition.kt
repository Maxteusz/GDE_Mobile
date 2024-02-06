package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DocumentPosition () : Serializable {
    @SerializedName("Ilosc")
    var amount: Double = 0.0
    @SerializedName("CenaJednostkowa")
    lateinit var valuePerUnit: Currency
    @SerializedName("Id")
    val  id: Int = 0
    @SerializedName("Towar")
    var cargo : Cargo? = null
    @SerializedName("Jednostka")
    var unit : Cargo.Unit? = null



    fun getAmountFullInfo () = amount.toString()  + " " + unit?.name
    fun getAmountValue () = amount.toString()




}