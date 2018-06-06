package br.com.mezzanotte.sicknessmanager.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister

@Dao
interface SicknessRegisterDao : GenericDao<SicknessRegister> {

    @Query("SELECT * FROM sicknessRegister WHERE sicknessRegisterId = :id")
    fun findById(id: Long): SicknessRegister?

    @Query("SELECT * from sicknessRegister")
    fun findAllLiveData(): LiveData<List<SicknessRegister>>

    @Query("SELECT * from sicknessRegister")
    fun findAll(): List<SicknessRegister>

    @Query("SELECT product_id, COUNT(product_id) as ocurrences FROM sicknessRegister WHERE statusImageId = :statusId  GROUP BY product_id ORDER BY ocurrences DESC LIMIT 1;")
    fun findMostOcurrenceOfStatus(statusId: Int): ProductCount

}