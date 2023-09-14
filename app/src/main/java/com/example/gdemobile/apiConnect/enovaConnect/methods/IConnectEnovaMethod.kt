package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.IDto

interface IConnectEnovaMethod {

    val methodName : String
    val methodService : String
    val dto : IDto


}