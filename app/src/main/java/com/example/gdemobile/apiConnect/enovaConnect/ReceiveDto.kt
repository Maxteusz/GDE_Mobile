package com.example.gdemobile.apiConnect.enovaConnect

import com.google.gson.annotations.SerializedName

class ReceiveDto <T> {
    @SerializedName("IsException")
    val isException : Boolean = false
    @SerializedName("ExceptionMessage")
    val exceptionMessage : String = ""
    @SerializedName("IsEmpty")
    val isEmpty : Boolean = false
    @SerializedName("ResultInstance")
    val  resultInstance : T? = null
}


