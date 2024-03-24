package com.example.gdemobile.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Cargo() : Serializable, Parcelable{
    @SerializedName("ID")
    val id: Int = 0
    @SerializedName("Kod")
    val code: String = ""
    @SerializedName("Nazwa")
    val name: String = ""
    @SerializedName("JednostkaPodstawowa")
    val mainUnit : String = ""
    lateinit var EAN : String
    @SerializedName("JednostkiDodatkowe")
    val additionalUnits  = listOf<String>("ddsds")




    constructor(parcel: Parcel) : this() {
        EAN = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(EAN)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cargo> {
        override fun createFromParcel(parcel: Parcel): Cargo {
            return Cargo(parcel)
        }

        override fun newArray(size: Int): Array<Cargo?> {
            return arrayOfNulls(size)
        }
    }


}