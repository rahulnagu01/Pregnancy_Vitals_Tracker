package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VitalEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vitalDao(): VitalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "vitals_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
