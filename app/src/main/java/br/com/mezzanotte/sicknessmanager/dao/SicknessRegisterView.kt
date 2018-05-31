package br.com.mezzanotte.sicknessmanager.dao

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import br.com.mezzanotte.sicknessmanager.model.Product
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister

class SicknessRegisterView {

    @Embedded
    var sicknessRegister: SicknessRegister? = null

    @Relation(
            parentColumn = "sicknessRegisterId",
            entityColumn = "product_id",
            entity = Product::class
    )
    var products: List<Product> = listOf()

}