package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DocumentPosition () : Serializable {
    @SerializedName("Ilosc")
    var amount: Quantity =  Quantity()
   @SerializedName("CenaZaSztuke")
     var valuePerUnit: Currency = Currency()
    @SerializedName("Id")
    val  id: Int = 0
    @SerializedName("Towar")
    var cargo : Cargo? = null



    fun getPerUnitString(): String = "$valuePerUnit \\ ${amount.symbol}"
    fun getAmountValue () = amount.toString()




}