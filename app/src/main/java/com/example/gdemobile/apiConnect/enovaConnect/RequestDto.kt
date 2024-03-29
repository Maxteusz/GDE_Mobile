package com.example.gdemobile.apiConnect.enovaConnect

import com.example.gdemobile.config.Config
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.google.gson.annotations.SerializedName

class RequestDto(connectionMethod: IConnectEnovaMethod) {
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
    private var methodArgsDto  = connectionMethod.dto



}

