package br.com.mezzanotte.sicknessmanager.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.mezzanotte.sicknessmanager.dao.ProductDao
import br.com.mezzanotte.sicknessmanager.dao.SicknessRegisterDao
import br.com.mezzanotte.sicknessmanager.model.Product
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister

@Database(entities = [(SicknessRegister::class), (Product::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sicknessRegisterDao() : SicknessRegisterDao
    abstract fun productDao() : ProductDao

}