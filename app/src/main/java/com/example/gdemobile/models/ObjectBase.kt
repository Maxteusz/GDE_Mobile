package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

open class ObjectBase (@SerializedName("id") val Id : String, @SerializedName("code") val Code : String, @SerializedName("name") val Name : String) {

}