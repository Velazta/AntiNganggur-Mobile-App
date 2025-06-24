package com.l0123118.ravelin.antinganggur.data.api

import com.l0123118.ravelin.antinganggur.data.api.model.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

// data/api/ApiService.kt
interface ApiService {
    @POST("auth/register-guest")
    suspend fun registerGuest(): AuthResponse

    @GET("user/profile")
    suspend fun getUserProfile(): Profile

    @POST("user/profile")
    suspend fun updateProfile(@Body profileData: Profile): Profile

    // Endpoint lain untuk experience dan education (sudah benar dari sebelumnya)
    @GET("user/experiences")
    suspend fun getExperiences(): List<Experience>

    @GET("user/educations")
    suspend fun getEducations(): List<Education>

    // Daftar endpoint lain akan ditambahkan di sini nanti
}