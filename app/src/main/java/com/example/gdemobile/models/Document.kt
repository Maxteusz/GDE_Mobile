package com.example.gdemobile.models

import android.os.Parcel
import android.os.Parcelable
import com.example.gdemobile.utils.DateFormat
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class Document() : Serializable {

    @SerializedName("Id")
    var id : String = ""
    @SerializedName("NumerDokumentu")
    var number : String = ""
    @SerializedName("Kontrahent")
    var contractor: Contractor? = null
    @SerializedName("DefinicjaDokumentu")
    var definition: DocumentDefinition? = null
    @SerializedName("DataWystawienia")
    var date : String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    @SerializedName("Opis")
    var describe : String =""
    var isNew  = false
    var documentPositions = mutableListOf<DocumentPosition>()




}