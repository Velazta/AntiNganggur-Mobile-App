package com.l0123118.ravelin.antinganggur.data.api.model

import com.google.gson.annotations.SerializedName

data class Cv(
    @SerializedName("id")
    val id: Int,

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("file_path")
    val filePath: String,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("file_size_kb")
    val fileSizeKb: Double,

    @SerializedName("mime_type")
    val mimeType: String?,

    @SerializedName("download_url")
    val downloadUrl: String,

    @SerializedName("uploaded_at")
    val uploadedAt: String,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("updated_at")
    val updatedAt: String?
)