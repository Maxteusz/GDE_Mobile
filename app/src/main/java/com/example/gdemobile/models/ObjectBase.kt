package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

open class ObjectBase (@field:SerializedName("id") val Id : String,@field:SerializedName("code") val Code : String,@field:SerializedName("name")val Name : String) {

}