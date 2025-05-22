package com.l0123118.ravelin.antinganggur

import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Menggunakan autoMirrored untuk RTL support
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Send
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.ui.theme.TextColorPrimary
import com.l0123118.ravelin.antinganggur.ui.theme.LightPinkBackground
import com.l0123118.ravelin.antinganggur.ui.theme.TextFieldGray
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
import com.l0123118.ravelin.antinganggur.ui.theme.PinkSecondary
import com.l0123118.ravelin.antinganggur.ui.theme.TextColorSecondary
import com.l0123118.ravelin.antinganggur.ui.theme.TextFieldBackground
import com.l0123118.ravelin.antinganggur.ui.theme.AppOrange
import com.l0123118.ravelin.antinganggur.ui.theme.AppMagenta
import com.l0123118.ravelin.antinganggur.ui.theme.TextGray
import com.l0123118.ravelin.antinganggur.ui.theme.BannerGradientStart
import com.l0123118.ravelin.antinganggur.ui.theme.BannerGradientEnd
import com.l0123118.ravelin.antinganggur.ui.theme.CheckboxRed
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme

class Contact : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANTINGANGGURTheme {
                ContactPage(navController = rememberNavController())
            }
        }
    }
}

val OrangePeel = Color(0xFFFF9F43)
val LightPeach = Color(0xFFFFF3E0) // Perkiraan warna background utama
val DarkText = Color(0xFF333333)
val MediumGrayText = Color(0xFF757575)
val LightGrayBorder = Color(0xFFE0E0E0)
val BlueSubmit = Color(0xFF1A73E8)
val YellowBackgroundForm = Color(0xFFFFD180)




//CODE 1
@Composable
fun ContactPage(navController: NavHostController) {
    val context = LocalContext.current
    var nameState by remember{ mutableStateOf("") }
    var emailState by remember{ mutableStateOf("") }
    var messageState by remember{ mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPeach)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "(OUR CONTACT)",
                    style = MaterialTheme.typography.bodySmall.copy(MediumGrayText),
                    fontSize = 12.sp
                )
                Text(
                    text = "Get Ready",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    ),
                    fontSize = 38.sp
                )
                Text(
                    text = "With Us",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = OrangePeel
                    ),
                    fontSize = 38.sp,
                    modifier = Modifier.padding(top = 0.dp)
                )
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(OrangePeel)
            ) {
                Image(
                    painter = painterResource(id= R.drawable.logoantinganggur),
                    contentDescription = "LocationMap",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        item{
            ContactInfoItem(
                icon = Icons.Outlined.LocationOn,
                title = "Location",
                details = listOf(
                    "Jalan Ir. Sutami 36-A, Kentingan,",
                    "Jebres, Surakarta, Jawa Tengah,",
                    "Indonesia 57126"
                )
            )
        }

        item{
            ContactInfoItem(
                icon = Icons.Outlined.Call,
                title = "Contact Person",
                details = listOf(
                    "+62 812 1950 0363"
                )
            )
        }

        item {
            ContactInfoItem(
                icon = Icons.Outlined.AccessTime,
                title = "Working Hours",
                details = listOf("Senin - Sabtu: 07:00-18:00")
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween // Atau SpaceEvenly
            ) {
                SocialMediaButton(
                    icon = Icons.Outlined.Info, // Ganti dengan ikon Instagram
                    text = "INSTAGRAM",
                    onClick = { /* TODO: Handle Instagram click */ },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                SocialMediaButton(
                    icon = Icons.Outlined.Send, // Ganti dengan ikon WhatsApp
                    text = "WHATSAPP",
                    onClick = { /* TODO: Handle WhatsApp click */ },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                SocialMediaButton(
                    icon = Icons.Outlined.AlternateEmail, // Ganti dengan ikon GitHub
                    text = "GITHUB",
                    onClick = { /* TODO: Handle GitHub click */ },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(YellowBackgroundForm)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoantinganggur),
                    contentDescription = "Contact Form Bg",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalDensity.current.run { 2400.dp / 3 })
                        .align(Alignment.TopCenter)
                )

                Card() {}
            }
        }
    }
}

//data class SocialMediaItem(
//    val icon: ImageVector,
//    val text: String,
//    val onClickAction: () -> Unit
//)
//
//val socialMediaItems = listOf(
//    SocialMediaItem(
//        icon = Icons.Outlined.Info,
//        text = "INSTAGRAM",
//        onClickAction = {}
//    ),
//    SocialMediaItem(
//        icon = Icons.Outlined.Send,
//        text = "WHATSAPP",
//        onClickAction = {}
//    ),
//    SocialMediaItem(
//        icon = Icons.Outlined.AlternateEmail,
//        text = "GITHUB",
//        onClickAction = {}
//    )
//)

@Composable
fun SocialMediaButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp), // Adjust height as per need
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkText),
        border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp, brush = SolidColor(LightGrayBorder))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = text, tint = DarkText, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun ContactInfoItem(icon: ImageVector,title: String, details: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
//        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = OrangePeel,
            modifier = Modifier
                .size(36.dp)
                .background(OrangePeel.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column() {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(MediumGrayText),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 6.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            details.forEach { detail ->
                Text(
                    text = detail,
                    style = MaterialTheme.typography.bodyLarge.copy(OrangePeel),
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 900)
@Composable
fun ContactPagePreview() {
    ANTINGANGGURTheme {
        ContactPage(navController = rememberNavController())
    }
}

