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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.l0123118.ravelin.antinganggur.MyApplication
import com.l0123118.ravelin.antinganggur.di.ViewModelFactory
import com.l0123118.ravelin.antinganggur.navigation.Screen
import com.l0123118.ravelin.antinganggur.ui.profile.ProfileUiState
import com.l0123118.ravelin.antinganggur.ui.profile.ProfileViewModel
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.LightPeach
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as MyApplication).appContainer.profileRepository)
    )
) {

    val profileState by viewModel.profileState.collectAsState()
    val imageCacheKey by viewModel.profileImageCacheKey.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserProfile()
    }

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
                profileState = profileState,
                imageCacheKey = imageCacheKey
            )
            Spacer(modifier = Modifier.height(40.dp))
            ProfileMenuList(navController)
        }
    }
}

@Composable
fun ProfileHeaderCard(
    profileState: ProfileUiState, // Terima state, bukan data mentah
    imageCacheKey: Long
) {
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
            when (profileState) {
                is ProfileUiState.Loading -> {
                    // Tampilan saat loading
                    CircularProgressIndicator(modifier = Modifier.size(110.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Memuat...", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
                is ProfileUiState.Success -> {
                    // Tampilan jika sukses
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${profileState.profile.profilePhotoUrl}?v=$imageCacheKey")
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = com.l0123118.ravelin.antinganggur.R.drawable.logoantinganggur),
                        error = painterResource(id = com.l0123118.ravelin.antinganggur.R.drawable.logoantinganggur),
                        contentDescription = "Foto Profil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = profileState.profile.name ?: "Pengguna", // Gunakan nama dari state
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                }
                is ProfileUiState.Error -> {
                    // Tampilan jika error
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error",
                        modifier = Modifier.size(110.dp),
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Gagal Memuat",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileMenuList(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ProfileMenuItem(
            icon = Icons.Default.AccountCircle,
            text = "Biodata Diri",
            backgroundColor = Color.White,
            contentColor = OrangePrimary,
            onClick = { navController.navigate(Screen.Biodata.route) }
        )
        ProfileMenuItem(
            icon = Icons.Default.BusinessCenter,
            text = "Pengalaman Kerja",
            backgroundColor = Color.White,
            contentColor = OrangePrimary,
            onClick = { navController.navigate(Screen.Experience.route) }
        )
        ProfileMenuItem(
            icon = Icons.Default.School,
            text = "Pendidikan",
            backgroundColor = Color.White,
            contentColor = OrangePrimary,
            onClick = { navController.navigate(Screen.Education.route) }
        )
        ProfileMenuItem(
            icon = Icons.Default.Description,
            text = "CV",
            backgroundColor = Color.White,
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
    val overlayColor = LightPeach.copy(alpha = 0.3f)
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
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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