package com.l0123118.ravelin.antinganggur.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0123118.ravelin.antinganggur.data.api.model.Education
import com.l0123118.ravelin.antinganggur.data.api.model.Experience
import com.l0123118.ravelin.antinganggur.data.api.model.Profile
import com.l0123118.ravelin.antinganggur.data.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// State untuk setiap bagian data
sealed interface ProfileUiState {
    object Loading : ProfileUiState
    data class Success(val profile: Profile) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}

sealed interface ExperienceUiState {
    object Loading : ExperienceUiState
    data class Success(val experiences: List<Experience>) : ExperienceUiState
    data class Error(val message: String) : ExperienceUiState
}

sealed interface EducationUiState {
    object Loading : EducationUiState
    data class Success(val educations: List<Education>) : EducationUiState
    data class Error(val message: String) : EducationUiState
}

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    // StateFlow untuk setiap jenis data
    private val _profileState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val profileState: StateFlow<ProfileUiState> = _profileState.asStateFlow()

    private val _experienceState = MutableStateFlow<ExperienceUiState>(ExperienceUiState.Loading)
    val experienceState: StateFlow<ExperienceUiState> = _experienceState.asStateFlow()

    private val _educationState = MutableStateFlow<EducationUiState>(EducationUiState.Loading)
    val educationState: StateFlow<EducationUiState> = _educationState.asStateFlow()

    // State untuk mengontrol proses update (misal: untuk loading di tombol simpan)
    private val _isUpdating = MutableStateFlow(false)
    val isUpdating: StateFlow<Boolean> = _isUpdating.asStateFlow()



    init {
        // Saat ViewModel pertama kali dibuat, langsung ambil semua data
        viewModelScope.launch {
            try {
                repository.checkAndRegisterGuestIfNeeded()
                fetchUserProfile()
//                fetchUserExperiences()
//                fetchUserEducations()
            } catch (e: Exception) {
                _profileState.value = ProfileUiState.Error("Gagal melakukan autentikasi awal.")
            }
        }
    }

    fun fetchUserProfile() {
        _profileState.value = ProfileUiState.Loading
        viewModelScope.launch {
            try {
                val profileData = repository.getUserProfile()
                _profileState.value = ProfileUiState.Success(profileData)
            } catch (e: Exception) {
                _profileState.value = ProfileUiState.Error("Gagal memuat profil: ${e.message}")
            }
        }
    }

//    fun fetchUserExperiences() {
//        _experienceState.value = ExperienceUiState.Loading
//        viewModelScope.launch {
//            try {
//                val experiences = repository.getExperiences()
//                _experienceState.value = ExperienceUiState.Success(experiences)
//            } catch (e: Exception) {
//                _experienceState.value = ExperienceUiState.Error("Gagal memuat pengalaman kerja.")
//            }
//        }
//    }

//    fun fetchUserEducations() {
//        _educationState.value = EducationUiState.Loading
//        viewModelScope.launch {
//            try {
//                val educations = repository.getEducations()
//                _educationState.value = EducationUiState.Success(educations)
//            } catch (e: Exception) {
//                _educationState.value = EducationUiState.Error("Gagal memuat riwayat pendidikan.")
//            }
//        }
//    }


    // State untuk menampilkan pesan Toast
    var showSuccessDialog by mutableStateOf(false)
    var dialogMessage by mutableStateOf("")
    var isUpdateSuccess by mutableStateOf(false)

    fun dialogMessageShown() {
        showSuccessDialog = false
        dialogMessage = ""
    }

    fun updateProfile(profileData: Profile) {
        viewModelScope.launch {
            _isUpdating.value = true
            try {
                // Panggil repository untuk update data
                val updatedProfile = repository.updateProfile(profileData)
                // Jika berhasil, update state UI dengan data baru dari server
                _profileState.value = ProfileUiState.Success(updatedProfile)

                dialogMessage = "Biodata berhasil disimpan"
                isUpdateSuccess = true
                showSuccessDialog = true
            } catch (e: Exception) {
                dialogMessage = "Gagal memperbarui biodata: ${e.message}"
                isUpdateSuccess = false
                showSuccessDialog = true
            } finally {
                _isUpdating.value = false // Selesai loading
            }
        }
    }
}

