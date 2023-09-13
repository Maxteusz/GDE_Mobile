package com.example.gdemobile.enovaConnect.methods

import com.example.gdemobile.enovaConnect.IDto
import com.example.gdemobile.enovaConnect.RequestDto

interface IConnectEnovaMethod {

    fun  getBody() : RequestDto
    val methodName : String
    val methodService : String


}