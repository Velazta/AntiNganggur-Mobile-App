package com.l0123118.ravelin.antinganggur.data.api.model

import com.google.gson.annotations.SerializedName


data class Profile(

    @SerializedName("id")
    val id: Int,

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("phone_number")
    val phoneNumber: String?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("province")
    val province: String?,

    @SerializedName("city")
    val city: String?,

    @SerializedName("date_of_birth")
    val dateOfBirth: String?,

    @SerializedName("gender")
    val gender: String?,

    @SerializedName("address")
    val address: String?,

    @SerializedName("bio")
    val bio: String?,

    @SerializedName("profile_photo_path")
    val profilePhotoPath: String?,

//    timestamps
    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("updated_at")
    val updatedAt: String?
)