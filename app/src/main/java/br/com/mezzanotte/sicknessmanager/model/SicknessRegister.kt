package br.com.mezzanotte.sicknessmanager.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import java.util.*

@Entity(tableName = "sicknessRegister")
data class SicknessRegister(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "produto") var produto: String,
        @ColumnInfo(name = "marca") var marca: String,
        @ColumnInfo(name = "dataConsumo") var dataConsumo: String,
        @ColumnInfo(name = "status") var status: String): Parcelable {

    constructor(parcel: Parcel) : this(
            null,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //parcel.writeLong(id!!)
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