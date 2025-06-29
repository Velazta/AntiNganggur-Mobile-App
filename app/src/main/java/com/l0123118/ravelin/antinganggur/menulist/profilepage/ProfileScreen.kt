package com.l0123118.ravelin.antinganggur.menulist.profilepage

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.navigation.Screen
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.LightPeach
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary

@Composable
fun ProfileScreen(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF9F9F9)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            ProfileHeaderCard(
                name = "Giselle Aespa",
                profilePictureUrl = null
            )
            Spacer(modifier = Modifier.height(40.dp))
            ProfileMenuList(navController)
        }
    }
}

@Composable
fun ProfileHeaderCard(name: String, profilePictureUrl: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightPeach
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Gunakan Coil atau Glide untuk memuat gambar dari URL di sini
            // Untuk sekarang kita gunakan placeholder
            Image(
                imageVector = Icons.Default.AccountCircle, // Placeholder
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(8.dp) // Beri padding agar ikon tidak terlalu besar
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = name, // Menggunakan nama dari parameter
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333) // Warna teks lebih gelap agar kontras
            )
        }
    }
}

@Composable
fun ProfileMenuList(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp) // Memberi jarak antar item menu
    ) {
        // 3. Membedakan style item menu pertama dengan yang lain
        ProfileMenuItem(
            icon = Icons.Default.AccountCircle,
            text = "Biodata Diri",
            backgroundColor = Color.White, // Style aktif sesuai desain
            contentColor = OrangePrimary,
            onClick = { navController.navigate(Screen.Biodata.route) }
        )
        ProfileMenuItem(
            icon = Icons.Default.BusinessCenter,
            text = "Pengalaman Kerja",
            backgroundColor = Color.White, // Style non-aktif
            contentColor = OrangePrimary,
            onClick = { navController.navigate(Screen.Experience.route) }
        )
        ProfileMenuItem(
            icon = Icons.Default.School,
            text = "Pendidikan",
            backgroundColor = Color.White, // Style non-aktif
            contentColor = OrangePrimary,
            onClick = { navController.navigate(Screen.Education.route) }
        )
        ProfileMenuItem(
            icon = Icons.Default.Description,
            text = "CV",
            backgroundColor = Color.White, // Style non-aktif
            contentColor = OrangePrimary,
            onClick = { navController.navigate(Screen.UploadCV.route) }
        )
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    text: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // 2. Tentukan warna overlay saat ditekan
    // LightPeach dengan opacity 30% (0.3f) biasanya lebih baik untuk overlay
    // daripada 80% agar tidak terlalu menutupi warna asli.
    val overlayColor = LightPeach.copy(alpha = 0.3f)

    // 3. Animasikan warna latar belakang
    // Saat ditekan, kita menggabungkan warna overlay di atas warna asli.
    // Saat tidak ditekan, kita kembali ke warna asli.
    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (isPressed) {
            overlayColor.compositeOver(backgroundColor)
        } else {
            backgroundColor
        },
        label = "background-color-animation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            // 4. Hubungkan interactionSource ke clickable dan hapus indikasi default (ripple)
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Hapus efek ripple default agar tidak tumpang tindih
                onClick = onClick
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        // 5. Gunakan warna yang sudah dianimasikan
        colors = CardDefaults.cardColors(containerColor = animatedBackgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(28.dp),
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = contentColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ANTINGANGGURTheme {
        ProfileScreen(navController = rememberNavController())
    }
}