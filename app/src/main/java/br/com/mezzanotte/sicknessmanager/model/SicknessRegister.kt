package br.com.mezzanotte.sicknessmanager.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class SicknessRegister(val produto: String, val marca: String, val dataConsumo: String,
                            val status: String): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(produto)
        parcel.writeString(marca)
        parcel.writeString(dataConsumo)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SicknessRegister> {
        override fun createFromParcel(parcel: Parcel): SicknessRegister {
            return SicknessRegister(parcel)
        }

        override fun newArray(size: Int): Array<SicknessRegister?> {
            return arrayOfNulls(size)
        }
    }
}