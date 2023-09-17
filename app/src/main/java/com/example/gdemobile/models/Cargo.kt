package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

open class Cargo(
    @SerializedName("IdTowaru")
    val id : String,

    val code: String,

    val name: String,

    val barcode: String,

    val unit : String){


}