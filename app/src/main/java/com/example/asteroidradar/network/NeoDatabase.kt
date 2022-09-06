package com.example.asteroidradar.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NeoJSONData::class], version = 6, exportSchema = false)
abstract class NeoDatabase : RoomDatabase() {
    abstract val neoJsonDao: NeoJsonDao

    companion object {
        @Volatile
        private var INSTANCE: NeoDatabase? = null
        fun getInstance(context: Context): NeoDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NeoDatabase::class.java,
                        "Neo_jsonData_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                }

                return instance
            }
        }
    }
}