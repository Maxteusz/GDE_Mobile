package com.example.gdemobile.enovaConnect

import com.example.gdemobile.config.Config
import com.example.gdemobile.enovaConnect.methods.IConnectEnovaMethod
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class RequestDto<T : Any>(connectionMethod: IConnectEnovaMethod) {
    @SerializedName("DatabaseHandle")
    var databaseHanlde: String = "APIGDE"

    @SerializedName("Operator")
    private val _operator = Config.usernameERP

    @SerializedName("Password")
    private val _password = Config.passwordERP

    @SerializedName("ServiceName")
    private var _serviceName = connectionMethod.methodService

    @SerializedName("MethodName")
    private var _methodName: String = connectionMethod.methodName

    @SerializedName("MethodArgs")
    private var methodArgsDto: MethodArgs<T>? = MethodArgs()

   private  class MethodArgs<T> {
        @SerializedName("dto")
        var dto: Dto<T> = Dto<T>()
    }

   private class Dto<T> {
        @SerializedName("Paginacja")
        var pagination  = PaginationDto(0,3);

    }

    class PaginationDto (@SerializedName("Pomin")val skip: Int,  @SerializedName("Pobierz")val take: Int ){


    }
}

