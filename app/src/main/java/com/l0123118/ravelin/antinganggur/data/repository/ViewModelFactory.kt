package com.l0123118.ravelin.antinganggur.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.l0123118.ravelin.antinganggur.data.repository.ProfileRepository
import com.l0123118.ravelin.antinganggur.ui.profile.ProfileViewModel

class ViewModelFactory(private val repository: ProfileRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}