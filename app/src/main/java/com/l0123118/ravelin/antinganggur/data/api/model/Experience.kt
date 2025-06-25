package com.l0123118.ravelin.antinganggur.data.api.model

import com.google.gson.annotations.SerializedName

data class Experience(
    @SerializedName("id")
    val id: Int,

    @SerializedName("user_id")
    val userId: Int? = null,

    @SerializedName("job_title")
    val jobTitle: String,

    @SerializedName("company_name")
    val companyName: String,

    @SerializedName("country")
    val country: String?,

    @SerializedName("city")
    val city: String?,

    @SerializedName("start_month")
    val startMonth: Int?,

    @SerializedName("start_year")
    val startYear: Int?,

    @SerializedName("end_month")
    val endMonth: Int?,

    @SerializedName("end_year")
    val endYear: Int?,

    @SerializedName("is_current_job")
    val isCurrentJob: Boolean,

    @SerializedName("description")
    val description: String?,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("updated_at")
    val updatedAt: String?

)