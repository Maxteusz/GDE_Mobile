package com.example.gdemobile.enovaConnect

import com.example.gdemobile.config.Config
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class RequestDto <T : Any> {
    @SerializedName("DatabaseHandle")
    var databaseHanlde: String = "APIGDE"

    @SerializedName("Operator")
    private val _operator = Config.usernameERP

    @SerializedName("Password")
   private val _password = Config.passwordERP
    @SerializedName("ServiceName")
    var serviceName = ""

    @SerializedName("MethodName")
    var methodName: String = ""

    @SerializedName("MethodArgs")
    var methodArgsDto: MethodArgs<T>? = MethodArgs()

    class MethodArgs<T> {
        @SerializedName("dto")
        var  dto: T? = null
    }

    fun toJson(): String {
        val gson = Gson()
        return gson.toJson(this)
    }


}

