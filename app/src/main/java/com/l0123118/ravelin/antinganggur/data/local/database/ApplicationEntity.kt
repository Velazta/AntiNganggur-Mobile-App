package com.l0123118.ravelin.antinganggur.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val phoneNumber: String,
    val email: String,
    val location: String,
    val education: String,
    val major: String,
    val experienceLevel: String,
    val cvUri: String?,
    val portfolioUri: String?,
    val jobTitle: String
)