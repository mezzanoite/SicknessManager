package br.com.mezzanotte.sicknessmanager.database

import android.arch.persistence.room.Room
import br.com.mezzanotte.sicknessmanager.SicknessManagerApplication
import br.com.mezzanotte.sicknessmanager.dao.SicknessRegisterDao


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
            /*dbInstance = Room.inMemoryDatabaseBuilder(appContext.applicationContext,
                    AppDatabase::class.java).allowMainThreadQueries().build()*/
        }
    }

    fun getSicknessRegisterDAO(): SicknessRegisterDao {
        return dbInstance.sicknessRegisterDao()
    }

}