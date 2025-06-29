package com.l0123118.ravelin.antinganggur.menulist.lamarpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.l0123118.ravelin.antinganggur.data.local.database.ApplicationEntity
import com.l0123118.ravelin.antinganggur.data.repository.ApplicationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApplicationViewModel(
    private val applicationRepository: ApplicationRepository
) : ViewModel() {

    private val _applications = MutableStateFlow<List<ApplicationEntity>>(emptyList())
    val applications: StateFlow<List<ApplicationEntity>> = _applications

    init {
        viewModelScope.launch {
            applicationRepository.getAllApplications().collect {
                _applications.value = it
            }
        }
    }

    fun submitApplication(application: ApplicationEntity) {
        viewModelScope.launch {
            applicationRepository.insertApplication(application)
        }
    }
}

class ApplicationViewModelFactory(
    private val applicationRepository: ApplicationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationViewModel::class.java)) {
            return ApplicationViewModel(applicationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
