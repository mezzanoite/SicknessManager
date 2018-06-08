package br.com.mezzanotte.sicknessmanager.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.OnConflictStrategy.ROLLBACK
import br.com.mezzanotte.sicknessmanager.model.Product
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister

/**
 * Dao genérico com as operações comuns aos Daos do app
 */
@Dao
interface GenericDao<T> {

    @Insert(onConflict = REPLACE)
    fun insert(obj: T): Long

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

}