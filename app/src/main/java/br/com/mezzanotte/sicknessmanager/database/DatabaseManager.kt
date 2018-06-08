package br.com.mezzanotte.sicknessmanager.database

import android.arch.persistence.room.Room
import br.com.mezzanotte.sicknessmanager.SicknessManagerApplication
import br.com.mezzanotte.sicknessmanager.dao.ProductDao
import br.com.mezzanotte.sicknessmanager.dao.SicknessRegisterDao

/**
 * Gerenciador da base de dados. Utilizado para instanciar um novo database do Room
 * associado ao seu respectivo Dao
 */
object DatabaseManager {

    private lateinit var dbInstance: AppDatabase

    init {
        val appContext = SicknessManagerApplication.getInstance().applicationContext

        synchronized(AppDatabase::class) {
            dbInstance = Room.databaseBuilder(
                    appContext,
                    AppDatabase::class.java,
                    "app.sqlite")
                    .allowMainThreadQueries()
                    .build()
        }
    }

    fun getSicknessRegisterDao(): SicknessRegisterDao {
        return dbInstance.sicknessRegisterDao()
    }

    fun getProductDao(): ProductDao {
        return dbInstance.productDao()
    }

}