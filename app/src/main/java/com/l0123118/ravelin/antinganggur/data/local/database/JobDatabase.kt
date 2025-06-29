package com.l0123118.ravelin.antinganggur.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [JobEntity::class, ApplicationEntity::class], version = 2)
abstract class JobDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
    abstract fun applicationDao(): ApplicationDao

    companion object {
        @Volatile private var INSTANCE: JobDatabase? = null

        fun getDatabase(context: Context): JobDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JobDatabase::class.java,
                    "job_database"
                )
                    .fallbackToDestructiveMigration() // agar bisa reset saat tambah table baru
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}