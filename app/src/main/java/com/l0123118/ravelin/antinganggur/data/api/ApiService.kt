package com.l0123118.ravelin.antinganggur.data.api

import com.l0123118.ravelin.antinganggur.data.api.model.*
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path

// data/api/ApiService.kt
interface ApiService {
    @POST("auth/register-guest")
    suspend fun registerGuest(): AuthResponse

    @GET("user/profile")
    suspend fun getUserProfile(): Profile

    @POST("user/profile")
    suspend fun updateProfile(@Body profileData: Profile): Profile

    @GET("user/experiences")
    suspend fun getExperiences(): List<Experience>

    // Tambahkan endpoint ini di dalam interface ApiService
    @POST("user/experiences")
    suspend fun addExperience(@Body experience: Experience) // Tidak perlu return body jika hanya pesan

    @DELETE("user/experiences/{id}")
    suspend fun deleteExperience(@Path("id") experienceId: Int)

    @GET("user/educations")
    suspend fun getEducations(): List<Education>

    @POST("user/educations")
    suspend fun addEducation(@Body education: Education)

    @DELETE("user/educations/{id}")
    suspend fun deleteEducation(@Path("id") educationId: Int)

    // Daftar endpoint lain akan ditambahkan di sini nanti
}