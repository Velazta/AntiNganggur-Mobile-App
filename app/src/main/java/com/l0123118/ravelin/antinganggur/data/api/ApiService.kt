package com.l0123118.ravelin.antinganggur.data.api

import com.google.gson.annotations.SerializedName
import com.l0123118.ravelin.antinganggur.data.api.model.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Streaming

// data/api/ApiService.kt
interface ApiService {
    @POST("auth/register-guest")
    suspend fun registerGuest(): AuthResponse

    @GET("user")
    suspend fun getAuthenticatedUser(): User

    @GET("user/profile")
    suspend fun getUserProfile(): Profile

    @POST("user/profile")
    suspend fun updateProfile(@Body profileData: Profile): Profile

    data class PhotoUploadResponse(
        @SerializedName("message") val message: String,
        @SerializedName("profile_photo_url") val profilePhotoUrl: String
    )

    @Multipart
    @POST("user/profile/photo")
    suspend fun updateProfilePhoto(@Part photo: MultipartBody.Part): PhotoUploadResponse


    @GET("user/experiences")
    suspend fun getExperiences(): List<Experience>

    @POST("user/experiences")
    suspend fun addExperience(@Body experience: Experience)

    @DELETE("user/experiences/{id}")
    suspend fun deleteExperience(@Path("id") experienceId: Int)

    @PUT("user/experiences/{id}")
    suspend fun updateExperience(@Path("id") experienceId: Int, @Body experience: Experience): Experience

    @GET("user/educations")
    suspend fun getEducations(): List<Education>

    @POST("user/educations")
    suspend fun addEducation(@Body education: Education)

    @DELETE("user/educations/{id}")
    suspend fun deleteEducation(@Path("id") educationId: Int)

    @PUT("user/educations/{id}")
    suspend fun updateEducation(@Path("id") educationId: Int, @Body education: Education): Education

    @GET("user/cvs")
    suspend fun getCvs(): List<Cv>

    @Multipart
    @POST("user/cv")
    suspend fun uploadCv(@Part cvFile: MultipartBody.Part): Cv

    @DELETE("user/cv/{id}")
    suspend fun deleteCv(@Path("id") cvId: Int)

    @Streaming
    @GET("user/cv/download/{id}")
    suspend fun downloadCv(@Path("id") cvId: Int): ResponseBody
    // Daftar endpoint lain akan ditambahkan di sini nanti
}