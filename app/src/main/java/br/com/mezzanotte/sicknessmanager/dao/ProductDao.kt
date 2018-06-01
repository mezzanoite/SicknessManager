package br.com.mezzanotte.sicknessmanager.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import br.com.mezzanotte.sicknessmanager.model.Product

@Dao
interface ProductDao : GenericDao<Product> {

    @Query("SELECT * FROM product WHERE productId = :id")
    fun findById(id: Long) : Product?

    @Query("SELECT * from product")
    fun findAll() : LiveData<List<Product>>

}