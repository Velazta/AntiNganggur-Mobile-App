package com.l0123118.ravelin.antinganggur.data.local.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Query("SELECT * FROM jobs ORDER BY createdAt DESC")
    fun getAllJobs(): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs WHERE isTrending = 1 ORDER BY createdAt DESC")
    fun getTrendingJobs(): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs WHERE isTrending = 0 ORDER BY createdAt DESC")
    fun getRecentJobs(): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs WHERE id = :jobId")
    suspend fun getJobById(jobId: Int): JobEntity?

    @Query("SELECT * FROM jobs WHERE title LIKE '%' || :query || '%' OR company LIKE '%' || :query || '%'")
    fun searchJobs(query: String): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs WHERE location LIKE '%' || :location || '%'")
    fun getJobsByLocation(location: String): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs LIMIT 1")
    suspend fun getAllJobsOnce(): List<JobEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobs(jobs: List<JobEntity>)

    @Update
    suspend fun updateJob(job: JobEntity)

    @Delete
    suspend fun deleteJob(job: JobEntity)

    @Query("DELETE FROM jobs")
    suspend fun deleteAllJobs()
}
