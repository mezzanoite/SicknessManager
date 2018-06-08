package br.com.mezzanotte.sicknessmanager.dao

import android.arch.persistence.room.ColumnInfo

/** Classe representativa do retorno do find abaixo:
 * @see SicknessRegisterDao.findMostOcurrenceOfStatus
 */
class ProductCount {

    @ColumnInfo(name = "product_id")
    var productId: Long = 0

    var ocurrences: Int = 0

}