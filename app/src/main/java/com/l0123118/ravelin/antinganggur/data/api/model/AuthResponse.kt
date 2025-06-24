package com.l0123118.ravelin.antinganggur.data.api.model

import com.google.gson.annotations.SerializedName


data class AuthResponse(
    @SerializedName("token")
    val token: String
)