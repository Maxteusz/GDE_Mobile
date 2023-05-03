package com.example.gdemobile.models

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

open class ObjectBase () {

    fun toJson() : String
    {
        val gson = Gson()
        return gson.toJson(this)
    }

    fun fromJson(string : String) : ObjectBase
    {
        val gson = Gson()
        return gson.fromJson(string,ObjectBase::class.java)
    }



}