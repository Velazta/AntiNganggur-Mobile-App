package com.l0123118.ravelin.antinganggur.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val company: String,
    val location: String,
    val type: String,
    val salary: String,
    val description: String,
    val iconResId: Int? = null,
    val isTrending: Boolean = false,
    val requirements: String? = null,
    val benefits: String? = null,
    val workingHours: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
