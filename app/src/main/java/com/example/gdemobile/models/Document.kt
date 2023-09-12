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
    @SerializedName("NumerDokumentu")
    var number : String = ""
    @SerializedName("Kontrahent")
    var contractor: Contractor? = null
    @SerializedName("DefinicjaDokumentu")
    var definition: DocumentDefinition? = null
    @SerializedName("DataWystawienia")
    var date : String = ""





}