package com.l0123118.ravelin.antinganggur.data.api.model

import com.google.gson.annotations.SerializedName

data class Education(
    @SerializedName("id")
    val id: Int,

    @SerializedName("user_id")
    val userId: Int? = null,

    @SerializedName("university_name")
    val universityName: String,

    @SerializedName("degree")
    val degree: String?,

    @SerializedName("major")
    val major: String?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("city")
    val city: String?,

    @SerializedName("start_month")
    val startMonth: String?,

    @SerializedName("start_year")
    val startYear: String?,

    @SerializedName("end_month")
    val endMonth: String?,

    @SerializedName("end_year")
    val endYear: String?,

    @SerializedName("is_currently_studying")
    val currentlyStudying: Boolean,

    @SerializedName("ipk")
    val ipk: Double?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("updated_at")
    val updatedAt: String?

)