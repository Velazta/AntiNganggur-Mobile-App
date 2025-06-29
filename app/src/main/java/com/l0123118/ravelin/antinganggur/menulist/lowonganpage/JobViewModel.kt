package com.l0123118.ravelin.antinganggur.menulist.lowonganpage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0123118.ravelin.antinganggur.data.repository.JobRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class JobViewModel(private val jobRepository: JobRepository) : ViewModel() {

    private val _allJobs = MutableStateFlow<List<Job>>(emptyList())
    val allJobs: StateFlow<List<Job>> = _allJobs.asStateFlow()

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
        loadTrendingJobs()
    }

    private fun initializeData() {
        viewModelScope.launch {
            jobRepository.initializeDefaultJobs()
        }
    }

    private fun loadJobs() {
        viewModelScope.launch {
            launch {
                jobRepository.getRecentJobs().collect { jobs ->
                    _recentJobs.value = jobs
                    _allJobs.value = jobs
                    _isLoading.value = false
                }
            }
            launch {
                jobRepository.getTrendingJobs().collect { trending ->
                    _trendingJobs.value = trending
                    Log.d("JobViewModel", "Trending Jobs Loaded: ${trending.size}")
                }
            }
        }
    }

    private fun loadTrendingJobs() {
        viewModelScope.launch {
            jobRepository.getTrendingJobs().collect { jobs ->
                _trendingJobs.value = jobs
            }
        }
    }

    fun searchJobs(query: String, location: String = "") {
        viewModelScope.launch {
            val jobs = jobRepository.getAllJobs().first()
            val filtered = jobs.filter { job ->
                val matchesKeyword = query.isBlank() || job.title.contains(query, ignoreCase = true) || job.company.contains(query, ignoreCase = true)
                val matchesLocation = location.isBlank() || location == "Semua Lokasi" || job.location.contains(location, ignoreCase = true)
                matchesKeyword && matchesLocation
            }.distinctBy { job ->
                "${job.title}-${job.company}-${job.location}"
            }

            _searchResults.value = filtered
        }
    }

    suspend fun getJobById(jobId: Int): Job? {
        return jobRepository.getJobById(jobId)
    }

    fun addJob(job: Job) {
        viewModelScope.launch {
            jobRepository.insertJob(job)
            loadJobs()
        }
    }

    fun updateJob(job: Job) {
        viewModelScope.launch {
            jobRepository.updateJob(job)
            loadJobs()
        }
    }

    fun deleteJob(job: Job) {
        viewModelScope.launch {
            jobRepository.deleteJob(job)
            loadJobs()
        }
    }
}
