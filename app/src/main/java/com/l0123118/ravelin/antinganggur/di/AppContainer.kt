package com.l0123118.ravelin.antinganggur.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.l0123118.ravelin.antinganggur.data.api.RetrofitClient
import com.l0123118.ravelin.antinganggur.data.local.TokenManager
import com.l0123118.ravelin.antinganggur.data.local.database.AppDatabase
import com.l0123118.ravelin.antinganggur.data.local.database.JobDatabase
import com.l0123118.ravelin.antinganggur.data.repository.ApplicationRepository
import com.l0123118.ravelin.antinganggur.data.repository.JobRepository
import com.l0123118.ravelin.antinganggur.data.repository.ProfileRepository

class AppContainer(context: Context) {
    private val tokenManager = TokenManager(context)
    private val appDatabase = JobDatabase.getDatabase(context)

    private val apiService = RetrofitClient.createService(tokenManager)

    val profileRepository = ProfileRepository(apiService, tokenManager)

    // Job Database
    private val jobDatabase = JobDatabase.getDatabase(context)
    private val jobDao = jobDatabase.jobDao()
    val jobRepository = JobRepository(jobDao)

    //Lamar database
    val applicationDao = appDatabase.applicationDao()
    val applicationRepository = ApplicationRepository(applicationDao)

}