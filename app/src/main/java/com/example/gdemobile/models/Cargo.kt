package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

open class Cargo(
    @SerializedName("Id")
    val id : String,
    @SerializedName("Symbol")
    val code: String,
    @SerializedName("Nazwa")
    val name: String,
    @SerializedName("Kod kreskowy")
    val barcode: String,
    @SerializedName("Jednostka")
    val unit : String){


}