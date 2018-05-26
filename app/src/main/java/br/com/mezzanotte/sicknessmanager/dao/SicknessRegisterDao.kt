package br.com.mezzanotte.sicknessmanager.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister

@Dao
interface SicknessRegisterDao {

    @Query("SELECT * FROM sicknessRegister WHERE id = :id")
    fun findById(id: Long) : SicknessRegister?

    @Query("SELECT * from sicknessRegister")
    fun findAll() : LiveData<List<SicknessRegister>>

    @Insert(onConflict = REPLACE)
    fun insert(sicknessRegister: SicknessRegister)

    @Delete
    fun deleteAll(sicknessRegister: SicknessRegister)

}