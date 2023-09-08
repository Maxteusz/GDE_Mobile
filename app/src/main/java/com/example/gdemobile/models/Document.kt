package com.example.gdemobile.models

import com.example.gdemobile.utils.DateFormat
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class Document {

    @SerializedName("Id")
    var id : String = "";
    @SerializedName("Kontrahent")
    var contractorId: String = ""
    var contractor: Contractor? = null
    var definition: DocumentDefinition? = null
    @SerializedName("DataWystawienia")
    var date : String = ""





}