package com.l0123118.ravelin.antinganggur.data.repository

import com.l0123118.ravelin.antinganggur.data.local.database.ApplicationDao
import com.l0123118.ravelin.antinganggur.data.local.database.ApplicationEntity
import kotlinx.coroutines.flow.Flow

class ApplicationRepository(private val dao: ApplicationDao) {
    suspend fun insertApplication(application: ApplicationEntity) {
        dao.insertApplication(application)
    }

    fun getAllApplications(): Flow<List<ApplicationEntity>> {
        return dao.getAllApplications()
    }
}