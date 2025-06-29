package com.l0123118.ravelin.antinganggur.data.local.database

import androidx.room.*
import com.l0123118.ravelin.antinganggur.data.local.database.ApplicationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApplication(application: ApplicationEntity)

    @Query("SELECT * FROM applications")
    fun getAllApplications(): Flow<List<ApplicationEntity>>

    @Delete
    suspend fun deleteApplication(application: ApplicationEntity)
}