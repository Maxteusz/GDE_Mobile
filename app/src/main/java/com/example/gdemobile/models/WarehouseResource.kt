package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class WarehouseResource {

    val ID : Int = 0;
    @SerializedName("Magazyn")
    val warehouse : Warehouse = Warehouse()
    @SerializedName("Towar")
    val cargo  : Cargo = Cargo()
    @SerializedName("Ilosc")
    val amount : Quantity = Quantity()
    @SerializedName("Dokument")
    val document  = Document()
    @SerializedName("Dostawca")
    val provider : Contractor? = null
}