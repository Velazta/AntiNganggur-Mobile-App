package com.l0123118.ravelin.antinganggur.data.repository

import android.util.Log
// Pastikan semua import model dari package yang benar
import com.l0123118.ravelin.antinganggur.data.api.model.Profile
import com.l0123118.ravelin.antinganggur.data.local.TokenManager
// Import ApiService YANG BENAR
import com.l0123118.ravelin.antinganggur.data.api.ApiService
import com.l0123118.ravelin.antinganggur.data.api.model.Experience
import com.l0123118.ravelin.antinganggur.data.api.model.Education

class ProfileRepository(
    // UBAH TIPE PARAMETER INI
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) {

    /**
     * Memeriksa apakah token sudah ada. Jika tidak, daftarkan sebagai guest.
     * Ini adalah fungsi kunci untuk alur "tanpa login".
     */
    suspend fun checkAndRegisterGuestIfNeeded() {
        if (tokenManager.getToken() == null) {
            try {
                // Sekarang 'apiService' akan mengenali method 'registerGuest()'
                val response = apiService.registerGuest()
                tokenManager.saveToken(response.token)
                Log.d("ProfileRepository", "Guest registered and token saved.")
            } catch (e: Exception) {
                // Tangani error, misalnya tidak ada koneksi internet
                Log.e("ProfileRepository", "Failed to register guest", e)
                // Anda bisa melempar exception khusus di sini jika perlu
                throw e
            }
        } else {
            Log.d("ProfileRepository", "Token already exists.")
        }
    }

    // --- Fungsi untuk Profil ---
    suspend fun getUserProfile(): Profile {
        // Dan juga method 'getUserProfile()'
        return apiService.getUserProfile()
    }

    suspend fun getExperiences(): List<Experience> {
        return apiService.getExperiences()
    }

    suspend fun updateProfile(profileData: Profile): Profile {
        return apiService.updateProfile(profileData)
    }

    // Tambahkan fungsi ini di dalam ProfileRepository
    suspend fun addExperience(experience: Experience) {
        apiService.addExperience(experience)
    }

    suspend fun deleteExperience(experienceId: Int) {
        apiService.deleteExperience(experienceId)
    }

    // --Fungsi untuk pengalaman edukasi
    suspend fun getEducations(): List<Education> {
        return apiService.getEducations()
    }

    // --- Fungsi untuk Experience ---
    // Anda perlu menambahkan endpoint yang sesuai di ApiService.kt
    // suspend fun getExperiences(): List<Experience> {
    //     return apiService.getExperiences()
    // }

    // --- Fungsi untuk Education ---
    // Anda perlu menambahkan endpoint yang sesuai di ApiService.kt
    // suspend fun getEducations(): List<Education> {
    //     return apiService.getEducations()
    // }

    // Tambahkan fungsi lain untuk CV, update data, dll. di sini
}