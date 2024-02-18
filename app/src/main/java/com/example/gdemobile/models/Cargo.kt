package com.example.gdemobile.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Cargo(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Kod")
    val code: String?,
    @SerializedName("Nazwa")
    val name: String?,
    @SerializedName("KodEan")
    val barcode: String?
) : Serializable, Parcelable{
    @SerializedName("JednostkaPodstawowa")
    val mainUnit = Unit()
    @SerializedName("DozwoloneJednostki")
    val additionalUnits = listOf(Unit())


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }


    class Unit()
    {
        @SerializedName("Id")
        var id : String = ""
        @SerializedName("Nazwa")
        val name = ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(code)
        parcel.writeString(name)
        parcel.writeString(barcode)
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