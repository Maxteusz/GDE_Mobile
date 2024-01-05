package com.example.gdemobile.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import java.io.Serializable

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Document() : Serializable, Parcelable {

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
    var documentPositions = mutableListOf<DocumentPosition>()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()!!
        number = parcel.readString()!!
        date = parcel.readString()!!
        describe = parcel.readString()!!

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(number)
        parcel.writeString(date)
        parcel.writeString(describe)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Document> {
        override fun createFromParcel(parcel: Parcel): Document {
            return Document(parcel)
        }

        override fun newArray(size: Int): Array<Document?> {
            return arrayOfNulls(size)
        }
    }




}