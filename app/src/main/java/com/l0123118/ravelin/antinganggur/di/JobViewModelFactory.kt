package com.l0123118.ravelin.antinganggur.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.l0123118.ravelin.antinganggur.data.repository.JobRepository
import com.l0123118.ravelin.antinganggur.menulist.lowonganpage.JobViewModel

class JobViewModelFactory(private val jobRepository: JobRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobViewModel(jobRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
