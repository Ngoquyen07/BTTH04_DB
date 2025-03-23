package com.example.btth04_db.db.appdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.btth04_db.db.dao.PersonDao
import com.example.btth04_db.db.entities.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun PersonDao() : PersonDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "person_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}