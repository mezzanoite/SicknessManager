package br.com.mezzanotte.sicknessmanager.model

import android.arch.persistence.room.*
import android.os.Parcel
import android.os.Parcelable
import br.com.mezzanotte.sicknessmanager.RegisterActivity

@Entity(tableName = "sicknessRegister",
        indices= arrayOf(Index(value = "product_id", name = "idx")),
        foreignKeys = arrayOf(ForeignKey(
                entity = Product::class,
                parentColumns = arrayOf("productId"),
                childColumns = arrayOf("product_id"))))
data class SicknessRegister(
        @PrimaryKey(autoGenerate = true) var sicknessRegisterId: Long?,
        var dataConsumo: String,
        var statusImageId: Int,
        @ColumnInfo(name = "product_id") var productId: Long): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        sicknessRegisterId?.let { parcel.writeLong(it) }
        parcel.writeString(dataConsumo)
        parcel.writeInt(statusImageId)
        parcel.writeLong(productId)
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