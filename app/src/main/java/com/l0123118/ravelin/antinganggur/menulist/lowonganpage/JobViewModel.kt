package com.l0123118.ravelin.antinganggur.menulist.lowonganpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0123118.ravelin.antinganggur.data.repository.JobRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JobViewModel(private val jobRepository: JobRepository) : ViewModel() {

    private val _trendingJobs = MutableStateFlow<List<Job>>(emptyList())
    val trendingJobs: StateFlow<List<Job>> = _trendingJobs.asStateFlow()

    private val _recentJobs = MutableStateFlow<List<Job>>(emptyList())
    val recentJobs: StateFlow<List<Job>> = _recentJobs.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Job>>(emptyList())
    val searchResults: StateFlow<List<Job>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        initializeData()
        loadJobs()
    }

    private fun initializeData() {
        viewModelScope.launch {
            jobRepository.initializeDefaultJobs()
        }
    }

    private fun loadJobs() {
        viewModelScope.launch {
            _isLoading.value = true

            // Load trending jobs
            jobRepository.getTrendingJobs().collect { jobs ->
                _trendingJobs.value = jobs
            }
        }

        viewModelScope.launch {
            // Load recent jobs
            jobRepository.getRecentJobs().collect { jobs ->
                _recentJobs.value = jobs
                _isLoading.value = false
            }
        }
    }

    fun searchJobs(query: String, location: String = "") {
        viewModelScope.launch {
            if (query.isBlank() && location.isBlank()) {
                _searchResults.value = emptyList()
                return@launch
            }

            val results = if (location.isNotBlank() && location != "Semua Lokasi") {
                jobRepository.getJobsByLocation(location)
            } else {
                jobRepository.searchJobs(query)
            }

            results.collect { jobs ->
                _searchResults.value = jobs.filter { job ->
                    if (query.isNotBlank()) {
                        job.title.contains(query, ignoreCase = true) ||
                                job.company.contains(query, ignoreCase = true)
                    } else {
                        true
                    }
                }
            }
        }
    }

    suspend fun getJobById(jobId: Int): Job? {
        return jobRepository.getJobById(jobId)
    }

    fun addJob(job: Job) {
        viewModelScope.launch {
            jobRepository.insertJob(job)
        }
    }

    fun updateJob(job: Job) {
        viewModelScope.launch {
            jobRepository.updateJob(job)
        }
    }

    fun deleteJob(job: Job) {
        viewModelScope.launch {
            jobRepository.deleteJob(job)
        }
    }
}
