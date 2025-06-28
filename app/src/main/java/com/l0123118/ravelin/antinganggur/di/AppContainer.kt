package com.l0123118.ravelin.antinganggur.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.l0123118.ravelin.antinganggur.data.api.RetrofitClient
import com.l0123118.ravelin.antinganggur.data.local.TokenManager
import com.l0123118.ravelin.antinganggur.data.local.database.JobDatabase
import com.l0123118.ravelin.antinganggur.data.repository.JobRepository
import com.l0123118.ravelin.antinganggur.data.repository.ProfileRepository
// Pastikan tidak ada import ApiService dari firebase di sini

class AppContainer(context: Context) {
    private val tokenManager = TokenManager(context)

    // Panggilan ini akan menghasilkan ApiService yang benar jika RetrofitClient sudah benar
    private val apiService = RetrofitClient.createService(tokenManager)

    // Maka, tidak akan ada error lagi di sini
    val profileRepository = ProfileRepository(apiService, tokenManager)

    // Job Database
    private val jobDatabase = JobDatabase.getDatabase(context)
    private val jobDao = jobDatabase.jobDao()

    // Repositories
    val jobRepository = JobRepository(jobDao)
}