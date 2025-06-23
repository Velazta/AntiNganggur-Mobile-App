package com.l0123118.ravelin.antinganggur.navigation

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
    object AboutUs : Screen("aboutuspage", "About Us", Icons.Filled.PersonAdd)
    object JobDetail : Screen("job_detail/{jobId}", "Detail Lowongan", Icons.Filled.Work) {
        fun createRoute(jobId: String): String {
            // URL encode the jobId to handle special characters
            val encodedJobId = java.net.URLEncoder.encode(jobId, "UTF-8")
            return "job_detail/$encodedJobId"
        }
    }
}

// Daftar item untuk drawer menu
val drawerMenuItems = listOf(
    Screen.Home,
    Screen.Login,
    Screen.SignIn,
    Screen.LowonganScreen,
    Screen.ContactPage,
    Screen.AboutUs,
)
