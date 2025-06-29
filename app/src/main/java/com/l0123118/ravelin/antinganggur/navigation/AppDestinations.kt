package com.l0123118.ravelin.antinganggur.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.Folder
import androidx.compose.ui.graphics.vector.ImageVector

// Definisikan Screen dengan route dan title
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Login : Screen("login", "Login", Icons.Filled.Login)
    object SignIn : Screen("signin", "Sign In", Icons.Filled.PersonAdd)
    object ContactPage : Screen("contact", "Contact Page", Icons.Filled.PhoneIphone)
    object LowonganScreen : Screen("lowongan", "Lowongan", Icons.Filled.Work)
    object Portofolio : Screen("portofolio", "Portofolio", Icons.Filled.Folder)
    object AboutUs : Screen("aboutuspage", "About Us", Icons.Filled.PersonAdd)
    object Profile : Screen("profile", "Profil Saya", Icons.Filled.AccountCircle)
    object JobDetail : Screen("job_detail/{jobId}", "Detail Lowongan", Icons.Filled.Work)
    {
        fun createRoute(jobId: String): String {
            val encodedJobId = java.net.URLEncoder.encode(jobId, "UTF-8")
            return "job_detail/$encodedJobId"
        }
    }

    // Definisikan juga screen untuk sub-menu profil agar navigasi nanti lebih mudah
    object Biodata : Screen("profile/biodata", "Biodata", Icons.Default.AccountCircle)
    object Experience : Screen("profile/experience", "Pengalaman Kerja", Icons.Default.Work)
    object Education : Screen("profile/education", "Pendidikan", Icons.Default.Work) // Ganti ikon jika perlu
    object UploadCV : Screen("profile/cv", "Upload CV", Icons.Default.Work) // Ganti ikon jika perlu
    object Lamar : Screen("lamar/{jobTitle}", "Lamar", Icons.Filled.Work) {
        fun createRoute(jobTitle: String): String {
            val encoded = java.net.URLEncoder.encode(jobTitle, "UTF-8")
            return "lamar/$encoded"
        }
    }
}

// Daftar item untuk drawer menu
val drawerMenuItems = listOf(
    Screen.Home,
    Screen.Profile,
    Screen.Login,
    Screen.SignIn,
    Screen.Portofolio,
    Screen.LowonganScreen,
    Screen.ContactPage,
    Screen.AboutUs,
)
