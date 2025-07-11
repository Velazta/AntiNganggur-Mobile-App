package com.l0123118.ravelin.antinganggur.data.repository

import android.util.Log
import com.l0123118.ravelin.antinganggur.data.api.ApiService
import com.l0123118.ravelin.antinganggur.data.api.ApiService.PhotoUploadResponse
import com.l0123118.ravelin.antinganggur.data.api.model.Cv
import com.l0123118.ravelin.antinganggur.data.api.model.Education
import com.l0123118.ravelin.antinganggur.data.api.model.Experience
import com.l0123118.ravelin.antinganggur.data.api.model.Profile
import com.l0123118.ravelin.antinganggur.data.local.TokenManager
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.HttpException

class ProfileRepository(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) {

    suspend fun checkAndRegisterGuestIfNeeded() {
        val token = tokenManager.getToken()

        if (token == null) {
            Log.d("ProfileRepository", "No token found. Registering new guest...")
            registerGuest()
        } else {
            Log.d("ProfileRepository", "Token found. Verifying...")
            try {
                apiService.getAuthenticatedUser()
                Log.d("ProfileRepository", "Token is valid.")
            } catch (e: Exception) {
                if (e is HttpException && e.code() == 401) {
                    Log.d("ProfileRepository", "Token is invalid (401). Registering new guest...")
                    registerGuest()
                } else {
                    Log.e("ProfileRepository", "Failed to verify token", e)
                    throw e
                }
            }
        }
    }

    private suspend fun registerGuest() {
        try {
            val response = apiService.registerGuest()
            tokenManager.saveToken(response.token)
            Log.d("ProfileRepository", "Guest registered and new token saved.")
        } catch (e: Exception) {
            Log.e("ProfileRepository", "Failed to register new guest during verification", e)
            throw e
        }
    }

    // --- Fungsi untuk Profil ---
    suspend fun getUserProfile(): Profile {
        return apiService.getUserProfile()
    }

    suspend fun updateProfile(profileData: Profile): Profile {
        return apiService.updateProfile(profileData)
    }

    suspend fun updateProfilePhoto(photoPart: MultipartBody.Part): PhotoUploadResponse {
        return apiService.updateProfilePhoto(photoPart)
    }

    // --- Fungsi untuk Experience ---
    suspend fun getExperiences(): List<Experience> {
        return apiService.getExperiences()
    }

    suspend fun addExperience(experience: Experience) {
        apiService.addExperience(experience)
    }

    suspend fun updateExperience(experience: Experience): Experience {
        return apiService.updateExperience(experience.id, experience)
    }

    suspend fun deleteExperience(experienceId: Int) {
        apiService.deleteExperience(experienceId)
    }

    // --- Fungsi untuk Education ---
    suspend fun getEducations(): List<Education> {
        return apiService.getEducations()
    }

    suspend fun addEducation(education: Education) {
        apiService.addEducation(education)
    }

    suspend fun updateEducation(education: Education): Education {
        return apiService.updateEducation(education.id, education)
    }

    suspend fun deleteEducation(educationId: Int) {
        apiService.deleteEducation(educationId)
    }

    // --- Fungsi untuk CV ---
    suspend fun getCvs(): List<Cv> {
        return apiService.getCvs()
    }

    suspend fun uploadCv(cvFile: MultipartBody.Part): Cv {
        return apiService.uploadCv(cvFile)
    }

    suspend fun deleteCv(cvId: Int) {
        apiService.deleteCv(cvId)
    }

    suspend fun downloadCv(cvId: Int): ResponseBody {
        return apiService.downloadCv(cvId)
    }
}