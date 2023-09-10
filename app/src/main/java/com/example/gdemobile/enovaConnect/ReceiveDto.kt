package com.example.gdemobile.enovaConnect

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class ReceiveDto <T> {
    @SerializedName("IsException")
    val isException : Boolean = false
    @SerializedName("ExceptionMessage")
    val exceptionMessage : String = ""
    @SerializedName("IsEmpty")
    val isEmpty : Boolean = false
    @SerializedName("ResultInstance")
    val resultInstance : List<T> = emptyList()
}


