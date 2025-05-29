package com.l0123118.ravelin.antinganggur.menulist.aboutuspage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.LightOrange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//
//// 1. Data Class
//data class CapabilityItem(
//    val icon: ImageVector, // Type is ImageVector
//    val title: String,
//    val description: String
//)
//
//// 2. ViewModel
//class CapabilitiesViewModel : ViewModel() {
//    private val _capabilities = MutableStateFlow<List<CapabilityItem>>(emptyList())
//    val capabilities: StateFlow<List<CapabilityItem>> = _capabilities.asStateFlow()
//
//    init {
//        loadCapabilities()
//    }
//
//    private fun loadCapabilities() {
//        _capabilities.value = listOf(
//            CapabilityItem(
//                icon = Icons.Filled.Settings,
//                title = "Pengembangan Solusi IT Kustom",
//                description = "mengembangkan solusi perangkat lunak yang disesuaikan dengan kebutuhan spesifik klien, mulai dari aplikasi mobile hingga sistem manajemen data"
//            ),
//            CapabilityItem(
//                icon = Icons.Filled.CloudUpload,
//                title = "Integrasi Teknologi Modern",
//                description = "Kami mengintegrasikan teknologi modern seperti AI, Big Data, dan Cloud Computing untuk memberikan solusi yang efisien dan inovatif."
//            ),
//            CapabilityItem(
//                icon = Icons.Filled.Security,
//                title = "Keamanan Cyber",
//                description = "Kami menawarkan layanan keamanan siber yang komprehensif untuk melindungi data dan infrastruktur IT Anda dari ancaman siber yang terus berkembang."
//            ),
//            CapabilityItem(
//                icon = Icons.Filled.Settings,
//                title = "Dukungan dan Pemeliharaan IT",
//                description = "Kami menyediakan layanan dukungan teknis dan pemeliharaan sistem berkelanjutan, memastikan infrastruktur IT klien berfungsi optimal dan selalu diperbarui dengan teknologi terbaru."
//            ),
//
//            )
//    }
//}
//
//// 3. Main Composable Section
//@Composable
//fun OurCapabilitiesSection(capabilitiesViewModel: CapabilitiesViewModel = viewModel()) {
//    val capabilities by capabilitiesViewModel.capabilities.collectAsState()
//
//    val lightPinkBackground = Color(0xFFFFF9F7)
//    val orangeColor = Color(0xFFF9794D)
//    val darkTextColor = Color(0xFF333333) // Color for "We Provide"
//    val descriptionItemColor = Color(0xFF555555) // Color for capability description text
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(lightPinkBackground)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//                .background(Color(0xFFFBCFE8)) // Pinkish background for hero image area
//        ) {
//            Image(
//                // Ensure 'smileman.png' (originally input_file_0.png) is in your res/drawable folder
//                painter = painterResource(id = R.drawable.smileman), // Using your specified R.drawable.smileman
//                contentDescription = "Our Capabilities Hero",
//                contentScale = ContentScale.Crop,
//                alignment = Alignment.TopCenter,
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 16.dp),
//            verticalAlignment = Alignment.Top
//        ) {
//            Box(
//                modifier = Modifier
//                    .width(5.dp)
//                    .height(60.dp)
//                    .background(Color.Black)
//            )
//            Spacer(modifier = Modifier.width(12.dp))
//            Text(
//                text = buildAnnotatedString {
//                    withStyle(style = SpanStyle(color = orangeColor, fontWeight = FontWeight.Bold, fontSize = 28.sp)) {
//                        append("Our Capabilities\n")
//                    }
//                    withStyle(style = SpanStyle(color = darkTextColor, fontWeight = FontWeight.Bold, fontSize = 28.sp)) {
//                        append("We Provide")
//                    }
//                },
//                lineHeight = 34.sp
//            )
//        }
//
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f),
//            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
//            verticalArrangement = Arrangement.spacedBy(24.dp)
//        ) {
//            items(capabilities) { capability -> // 'capability' is of type CapabilityItem
//                CapabilityRowItem(
//                    icon = capability.icon, // Should resolve to ImageVector
//                    title = capability.title, // Should resolve to String
//                    description = capability.description, // Should resolve to String
//                    iconBackgroundColor = orangeColor,
//                    titleColor = orangeColor, // Title text color for the item
//                    descriptionColor = descriptionItemColor // Description text color for the item
//                )
//            }
//        }
//    }
//}
//
//// 4. Composable for each row item in LazyColumn
//@Composable
//fun CapabilityRowItem(
//    icon: ImageVector,
//    title: String,
//    description: String,
//    iconBackgroundColor: Color,
//    titleColor: Color,
//    descriptionColor: Color
//) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.Top
//    ) {
//        Box(
//            modifier = Modifier
//                .size(56.dp)
//                .clip(CircleShape)
//                .background(iconBackgroundColor)
//                .padding(12.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = title,
//                tint = Color.White,
//                modifier = Modifier.size(28.dp)
//            )
//        }
//
//        Spacer(modifier = Modifier.width(16.dp))
//
//        Column(modifier = Modifier.weight(1f)) {
//            Text(
//                text = title,
//                color = titleColor,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                lineHeight = 22.sp
//            )
//            Spacer(modifier = Modifier.height(6.dp))
//            Text(
//                text = description,
//                color = descriptionColor,
//                fontSize = 14.sp,
//                lineHeight = 20.sp
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true, widthDp = 360, heightDp = 780)
//@Composable
//fun OurCapabilitiesSectionPreview() {
//    // Deklarasikan instance ViewModel di sini untuk digunakan oleh preview
//    val previewViewModel: CapabilitiesViewModel= viewModel()
//
//    // Pastikan ANTINGANGGURTheme adalah tema Material (M2 atau M3) aplikasi Anda
//    // dan sudah diimpor dengan benar jika berada di file lain.
//    ANTINGANGGURTheme {
//        OurCapabilitiesSection(capabilitiesViewModel = previewViewModel)
//    }
//}


data class CapabilityItem(
    val icon: ImageVector,
    val title: String,
    val description: String
)

class CapabilitiesViewModel : ViewModel() {
    private val _capabilities = MutableStateFlow<List<CapabilityItem>>(emptyList())
    val capabilities: StateFlow<List<CapabilityItem>> = _capabilities.asStateFlow()

    init {
        loadCapabilities()
    }

    private fun loadCapabilities() {
        _capabilities.value = listOf(
            CapabilityItem(Icons.Filled.Settings, "Pengembangan Solusi IT Kustom", "mengembangkan solusi perangkat lunak yang disesuaikan dengan kebutuhan spesifik klien, mulai dari aplikasi mobile hingga sistem manajemen data"),
            CapabilityItem(Icons.Filled.CloudUpload, "Integrasi Teknologi Modern", "Kami mengintegrasikan teknologi modern seperti AI, Big Data, dan Cloud Computing untuk memberikan solusi yang efisien dan inovatif."),
            CapabilityItem(Icons.Filled.Security, "Keamanan Cyber", "Kami menawarkan layanan keamanan siber yang komprehensif untuk melindungi data dan infrastruktur IT Anda dari ancaman siber yang terus berkembang."),
            CapabilityItem(Icons.Filled.Settings, "Dukungan dan Pemeliharaan IT", "Kami menyediakan layanan dukungan teknis dan pemeliharaan sistem berkelanjutan, memastikan infrastruktur IT klien berfungsi optimal dan selalu diperbarui dengan teknologi terbaru.")
        )
    }
}

@Composable
fun CapabilitiesImageSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color(0xFFFBCFE8)) // Latar belakang pink muda untuk area gambar
    ) {
        Image(
            painter = painterResource(id = R.drawable.smileman),
            contentDescription = "Our Capabilities Hero",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun CapabilitiesTitleContent(orangeColor: Color, darkTextColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .width(5.dp)
                .height(60.dp)
                .background(Color.Black)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = orangeColor, fontWeight = FontWeight.Bold, fontSize = 28.sp)) {
                    append("Our Capabilities\n")
                }
                withStyle(style = SpanStyle(color = darkTextColor, fontWeight = FontWeight.Bold, fontSize = 28.sp)) {
                    append("We Provide")
                }
            },
            lineHeight = 34.sp
        )
    }
}

@Composable
fun CapabilityRowItem(
    icon: ImageVector,
    title: String,
    description: String,
    iconBackgroundColor: Color,
    titleColor: Color,
    descriptionColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(iconBackgroundColor)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, color = titleColor, fontSize = 18.sp, fontWeight = FontWeight.Bold, lineHeight = 22.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = description, color = descriptionColor, fontSize = 14.sp, lineHeight = 20.sp)
        }
    }
}

@Preview(showBackground = true, widthDp = 360, name = "Capabilities Content Preview")
@Composable
fun CapabilitiesContentPreview() {
    val capabilitiesViewModel: CapabilitiesViewModel = viewModel()
    val capabilities by capabilitiesViewModel.capabilities.collectAsState()
    val orangeColor = Color(0xFFFF6F3E)
    val darkTextColorForTitle = Color(0xFF333333)
    val lightPinkBackground = Color(0xFFFFF9F7)
    val descriptionItemColor = Color(0xFF555555)

    ANTINGANGGURTheme {
        LazyColumn(modifier = Modifier.background(LightOrange)) {
            item { CapabilitiesImageSection() }
            item {
                Column(modifier = Modifier.fillMaxWidth().background(lightPinkBackground)) {
                    CapabilitiesTitleContent(orangeColor = orangeColor, darkTextColor = darkTextColorForTitle)
                    Spacer(modifier = Modifier.height(8.dp))
                    capabilities.forEachIndexed { index, capability ->
                        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                            CapabilityRowItem(
                                icon = capability.icon,
                                title = capability.title,
                                description = capability.description,
                                iconBackgroundColor = orangeColor,
                                titleColor = orangeColor,
                                descriptionColor = descriptionItemColor
                            )
                        }
                        if (index < capabilities.size - 1) {
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                    if (capabilities.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}