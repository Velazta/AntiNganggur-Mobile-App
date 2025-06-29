package com.l0123118.ravelin.antinganggur.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ApplicationEntity::class, JobEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun applicationDao(): ApplicationDao
    abstract fun jobDao(): JobDao
}