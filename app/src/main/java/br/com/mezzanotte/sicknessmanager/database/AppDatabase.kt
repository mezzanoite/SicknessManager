package br.com.mezzanotte.sicknessmanager.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.mezzanotte.sicknessmanager.dao.SicknessRegisterDao
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister

@Database(entities = arrayOf(SicknessRegister::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sicknessRegisterDao() : SicknessRegisterDao

    /*companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    //INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    //        AppDatabase::class.java, "weather.db")
                    //        .build()
                    INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext,
                            AppDatabase::class.java).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }*/

}