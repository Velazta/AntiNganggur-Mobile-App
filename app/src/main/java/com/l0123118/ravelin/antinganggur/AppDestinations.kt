package com.l0123118.ravelin.antinganggur

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.ui.graphics.vector.ImageVector

// Definisikan Screen dengan route dan title
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Login : Screen("login", "Login", Icons.Filled.Login)
    object SignIn : Screen("signin", "Sign In", Icons.Filled.PersonAdd)
}

// Daftar item untuk drawer menu
val drawerMenuItems = listOf(
    Screen.Home,
    Screen.Login,
    Screen.SignIn
    // Anda bisa menambahkan Screen.Contact di sini jika ingin menampilkannya di drawer
)