package com.l0123118.ravelin.antinganggur

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

// Definisikan Screen dengan route dan title
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Login : Screen("login", "Login", Icons.Filled.Login)
    object SignIn : Screen("signin", "Sign In", Icons.Filled.PersonAdd)
    object ContactPage : Screen("contact", "Contact Page", Icons.Filled.PhoneIphone)
    object LowonganScreen : Screen("lowongan", "Lowongan", Icons.Filled.Work)
}

// Daftar item untuk drawer menu
val drawerMenuItems = listOf(
    Screen.Home,
    Screen.Login,
    Screen.SignIn,
    Screen.ContactPage,
    Screen.LowonganScreen

)