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

class Document() : Serializable,Parcelable {

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

    constructor(parcel: Parcel) : this() {
        id = parcel.readString().toString()
        number = parcel.readString().toString()
        date = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(number)
        parcel.writeString(date)
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