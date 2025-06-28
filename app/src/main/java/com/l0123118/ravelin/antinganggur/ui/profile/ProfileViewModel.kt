package com.l0123118.ravelin.antinganggur.ui.profile

import android.content.Context
import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0123118.ravelin.antinganggur.data.api.model.Cv
import com.l0123118.ravelin.antinganggur.data.api.model.Education
import com.l0123118.ravelin.antinganggur.data.api.model.Experience
import com.l0123118.ravelin.antinganggur.data.api.model.Profile
import com.l0123118.ravelin.antinganggur.data.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

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

sealed interface CvUiState {
    object Loading : CvUiState
    data class Success(val cvs: List<Cv>) : CvUiState
    data class Error(val message: String) : CvUiState
}

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    // StateFlow untuk setiap jenis data
    private val _profileState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val profileState: StateFlow<ProfileUiState> = _profileState.asStateFlow()

    private val _experienceState = MutableStateFlow<ExperienceUiState>(ExperienceUiState.Loading)
    val experienceState: StateFlow<ExperienceUiState> = _experienceState.asStateFlow()

    private val _educationState = MutableStateFlow<EducationUiState>(EducationUiState.Loading)
    val educationState: StateFlow<EducationUiState> = _educationState.asStateFlow()

    private val _cvState = MutableStateFlow<CvUiState>(CvUiState.Loading)
    val cvState: StateFlow<CvUiState> = _cvState.asStateFlow()

    // State untuk mengontrol proses update (misal: untuk loading di tombol simpan)
    private val _isUpdating = MutableStateFlow(false)
    val isUpdating: StateFlow<Boolean> = _isUpdating.asStateFlow()

    private val _isUploading = MutableStateFlow(false)
    val isUploading: StateFlow<Boolean> = _isUploading.asStateFlow()



    init {
        // Saat ViewModel pertama kali dibuat, langsung ambil semua data
        viewModelScope.launch {
            try {
                repository.checkAndRegisterGuestIfNeeded()
                fetchUserProfile()
                fetchUserExperiences()
                fetchUserEducations()
                fetchUserCvs()
            } catch (e: Exception) {
                val errorMessage = "Gagal melakukan autentikasi awal."
                _profileState.value = ProfileUiState.Error(errorMessage)
                _experienceState.value = ExperienceUiState.Error(errorMessage)
                _educationState.value = EducationUiState.Error(errorMessage)
                _cvState.value = CvUiState.Error(errorMessage)
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


    fun fetchUserExperiences() {
        _experienceState.value = ExperienceUiState.Loading
        viewModelScope.launch {
            try {
                val experiences = repository.getExperiences()
                _experienceState.value = ExperienceUiState.Success(experiences)
            } catch (e: Exception) {
                _experienceState.value = ExperienceUiState.Error("Gagal memuat pengalaman kerja.")
            }
        }
    }

    fun fetchUserEducations() {
        _educationState.value = EducationUiState.Loading
        viewModelScope.launch {
            try {
                val educations = repository.getEducations()
                _educationState.value = EducationUiState.Success(educations)
            } catch (e: Exception) {
                _educationState.value = EducationUiState.Error("Gagal memuat riwayat pendidikan.")
            }
        }
    }

    fun fetchUserCvs() {
        _cvState.value = CvUiState.Loading
        viewModelScope.launch {
            try {
                val cvs = repository.getCvs()
                _cvState.value = CvUiState.Success(cvs)
            } catch (e: Exception) {
                _cvState.value = CvUiState.Error("Gagal memuat daftar CV.")
            }
        }
    }


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

    fun addExperience(experience: Experience) {
        viewModelScope.launch {
            try {
                repository.addExperience(experience)
                fetchUserExperiences() // Muat ulang daftar setelah berhasil menambah
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun updateExperience(experience: Experience) {
        viewModelScope.launch {
            try {
                repository.updateExperience(experience)
                // Muat ulang daftar untuk menampilkan data yang sudah diperbarui
                fetchUserExperiences()
                // Anda bisa tambahkan logika dialog/toast sukses di sini jika mau
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun deleteExperience(experienceId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteExperience(experienceId)
                fetchUserExperiences() // Muat ulang daftar setelah berhasil menghapus
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun addEducation(education: Education) {
        viewModelScope.launch {
            try {
                repository.addEducation(education)
                fetchUserEducations() // Muat ulang daftar setelah berhasil menambah
            } catch (e: Exception) {
                // Handle error, misalnya dengan Toast atau Dialog
            }
        }
    }

    fun updateEducation(education: Education) {
        viewModelScope.launch {
            try {
                repository.updateEducation(education)
                // Muat ulang daftar untuk menampilkan data yang sudah diperbarui
                fetchUserEducations()
                // Anda bisa tambahkan logika dialog/toast sukses di sini
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun deleteEducation(educationId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteEducation(educationId)
                fetchUserEducations() // Muat ulang daftar setelah berhasil menghapus
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun uploadCv(cvFilePart: MultipartBody.Part) {
        viewModelScope.launch {
            _isUploading.value = true
            try {
                repository.uploadCv(cvFilePart)
                fetchUserCvs() // Muat ulang daftar CV setelah berhasil upload

                // Beri feedback sukses
                dialogMessage = "CV berhasil diunggah!"
                isUpdateSuccess = true
                showSuccessDialog = true

            } catch (e: Exception) {
                // Beri feedback error
                dialogMessage = "Gagal mengunggah CV: ${e.message}"
                isUpdateSuccess = false
                showSuccessDialog = true
            } finally {
                _isUploading.value = false
            }
        }
    }

    fun deleteCv(cvId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteCv(cvId)
                fetchUserCvs() // Muat ulang daftar CV
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun downloadCvFile(
        context: Context,
        cv: Cv,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val responseBody = repository.downloadCv(cv.id)

                // Simpan file ke folder Downloads
                val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val file = File(downloadsDir, cv.originalName)

                var inputStream: InputStream? = null
                var outputStream: OutputStream? = null

                try {
                    inputStream = responseBody.byteStream()
                    outputStream = FileOutputStream(file)
                    val buffer = ByteArray(4096)
                    var read: Int
                    while (inputStream.read(buffer).also { read = it } != -1) {
                        outputStream.write(buffer, 0, read)
                    }
                    outputStream.flush()
                    onSuccess("CV '${cv.originalName}' berhasil diunduh ke folder Downloads.")
                } catch (e: IOException) {
                    onError("Gagal menyimpan file: ${e.message}")
                } finally {
                    inputStream?.close()
                    outputStream?.close()
                }

            } catch (e: Exception) {
                onError("Gagal memulai download: ${e.message}")
            }
        }
    }
}



