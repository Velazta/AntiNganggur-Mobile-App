//package com.l0123118.ravelin.antinganggur
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.interaction.collectIsPressedAsState
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.drawBehind
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Path
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.constraintlayout.compose.Dimension
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.asPaddingValues
//import androidx.compose.foundation.layout.statusBars
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.automirrored.outlined.Send
//import androidx.compose.material.icons.filled.Call
//import androidx.compose.material.icons.filled.CloudUpload
//import androidx.compose.material.icons.filled.Security
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.outlined.AccessTime
//import androidx.compose.material.icons.outlined.AlternateEmail
////import androidx.compose.material.icons.outlined.Call // Duplicate import, can be removed if the filled one is preferred
//import androidx.compose.material.icons.outlined.Info
//import androidx.compose.material.icons.outlined.LocationOn
////import androidx.compose.material.icons.outlined.Send // Duplicate import, can be removed if the automirrored.outlined is preferred
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.geometry.Rect
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Outline
//import androidx.compose.ui.graphics.SolidColor
//import androidx.compose.ui.graphics.drawscope.DrawScope
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.unit.Density
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.LayoutDirection
////import androidx.constraintlayout.widget.ConstraintLayout // This is for View system, not Compose. Remove if not used elsewhere for View interop.
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.foundation.lazy.items
//import com.l0123118.ravelin.antinganggur.ui.theme.TextColorPrimary
//import com.l0123118.ravelin.antinganggur.ui.theme.LightPinkBackground
//import com.l0123118.ravelin.antinganggur.ui.theme.TextFieldGray
//import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
//import com.l0123118.ravelin.antinganggur.ui.theme.PinkSecondary
//import com.l0123118.ravelin.antinganggur.ui.theme.TextColorSecondary
//import com.l0123118.ravelin.antinganggur.ui.theme.TextFieldBackground
//import com.l0123118.ravelin.antinganggur.ui.theme.AppOrange
//import com.l0123118.ravelin.antinganggur.ui.theme.AppMagenta
//import com.l0123118.ravelin.antinganggur.ui.theme.TextGray
//import com.l0123118.ravelin.antinganggur.ui.theme.BannerGradientStart
//import com.l0123118.ravelin.antinganggur.ui.theme.BannerGradientEnd
//import com.l0123118.ravelin.antinganggur.ui.theme.CheckboxRed
//import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
//import com.l0123118.ravelin.antinganggur.ui.theme.BackgroundOrange
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//
//val OrangeAAG = Color(0xFFFF7A59)
//val TextWhiteAAG = Color.White
//val BackgroundImageAAG = Color(0xFFFFF6F4)
//val ButtonBorderAAG = Color.White
//
//
//class AboutUS : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            ANTINGANGGURTheme {
//                AboutUsPage(navController = rememberNavController())
//            }
//        }
//    }
//}
//
//@Composable
//fun AboutUsPage(navController: NavController) {
//
//}
//
//@Composable
//fun latarBelakang() {
//    val orangeColor = Color(0xFFF9794D)
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//            .background(BackgroundImageAAG)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight()
//                .drawBehind {
//                    val path = Path().apply {
//                        moveTo(0f, 0f)
//                        lineTo(size.width, 0f)
//                        lineTo(size.width, size.height * 0.80f)
//                        quadraticBezierTo(
//                            x1 = size.width / 2f,
//                            y1 = size.height * 1.15f,
//                            x2 = 0f,
//                            y2 = size.height * 0.80f
//                        )
//                        close()
//                    }
//                    drawPath(
//                        path = path,
//                        color = orangeColor
//                    )
//                }
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp)
//                    .padding(top = 40.dp, bottom = 70.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Selamat Datang Di Perusahaan Kami",
//                    color = Color.White,
//                    fontSize = 30.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Left,
//                    lineHeight = 38.sp
//                )
//                Spacer(modifier = Modifier.height(24.dp))
//                Text(
//                    text = "AntiNganggur didirikan pada tahun 2020 oleh sekelompok profesional IT yang melihat adanya kesenjangan antara kebutuhan industri dan keterampilan lulusan teknologi di Indonesia.",
//                    color = Color.White,
//                    fontSize = 15.sp,
//                    textAlign = TextAlign.Left,
//                    lineHeight = 22.sp
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "Berawal dari komunitas kecil yang berbagi pengetahuan secara online, kami berkembang menjadi platform komprehensif yang menghubungkan talenta IT dengan peluang karir dan pembelajaran yang relevan dengan kebutuhan industri.",
//                    color = Color.White,
//                    fontSize = 15.sp,
//                    textAlign = TextAlign.Left,
//                    lineHeight = 22.sp
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "Saat ini, AntiNganggur telah membantu lebih dari 5.000 profesional IT menemukan pekerjaan impian mereka dan membekali lebih dari 15.000 pembelajar dengan keterampilan digital yang dibutuhkan di era modern.",
//                    color = Color.White,
//                    fontSize = 15.sp,
//                    textAlign = TextAlign.Left,
//                    lineHeight = 22.sp
//                )
//                Spacer(modifier = Modifier.height(32.dp))
//                OutlinedButton(
//                    onClick = { },
//                    shape = RoundedCornerShape(50),
//                    border = BorderStroke(1.5.dp, Color.White),
//                    colors = ButtonDefaults.outlinedButtonColors(
//                        contentColor = Color.White,
//                        containerColor = Color.Transparent
//                    ),
//                    modifier = Modifier.defaultMinSize(minWidth = 180.dp, minHeight = 48.dp)
//                ) {
//                    Text(
//                        "Lihat Detail",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium
//                    )
//                }
//            }
//        }
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(250.dp)
//                .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp))
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ilustrationaboutpage),
//                contentDescription = "Ilustrasi Perusahaan",
//                contentScale = ContentScale.Crop,
//                alignment = Alignment.BottomCenter,
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun latabelakangPreview() {
//    ANTINGANGGURTheme {
//        latarBelakang()
//    }
//}
//
//
//@Composable
//fun whatwedo() {
//    val imageGradientColors = listOf(Color(0xFFFFF6F4), Color(0xFFFFF6F4))
//    val descriptionBackgroundColor = Color(0xFFFFF6F4)
//    val bannerOrangeColor = Color(0xFFF9794D)
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//            .background(descriptionBackgroundColor)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    Brush.linearGradient(
//                        colors = imageGradientColors,
//                        start = Offset.Zero,
//                        end = Offset.Infinite
//                    )
//                )
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.whatwedobanner),
//                contentDescription = "What We Do Illustration",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 50.dp)
//                    .aspectRatio(4f / 3f)
//                    .clip(RoundedCornerShape(8.dp))
//            )
//        }
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .offset(y = (-28).dp)
//                    .padding(horizontal = 20.dp)
//            ) {
//                WhatWeDoBannerComposable(
//                    backgroundColor = bannerOrangeColor,
//                    text = "What We Do"
//                )
//            }
//
//            Text(
//                text = "AntiNganggur.id didirikan untuk menjadi solusi digital dalam mengatasi permasalahan pengangguran di sektor IT. Kami menyediakan platform yang menghubungkan perusahaan dengan kandidat berkualitas, menawarkan informasi yang akurat dan relevan tentang lowongan pekerjaan serta profil perusahaan. Dengan pendekatan yang proaktif, kami berkomitmen untuk memberdayakan pencari kerja dan membantu mereka menemukan peluang karir yang tepat. Hingga saat ini, AntiNganggur.id telah menjembatani ribuan profesional IT dengan peluang kerja yang sesuai, berkontribusi pada pertumbuhan industri teknologi informasi di Indonesia.",
//                color = Color.Black,
//                fontSize = 15.sp,
//                lineHeight = 24.sp,
//                textAlign = TextAlign.Justify,
//                modifier = Modifier
//                    .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
//            )
//        }
//    }
//}
//
//@Composable
//fun WhatWeDoBannerComposable(backgroundColor: Color, text: String) {
//    val bannerHeight = 48.dp
//    val pointExtension = bannerHeight * 0.4f
//    val textHorizontalPadding = 20.dp
//
//    Box(
//        contentAlignment = Alignment.CenterStart,
//        modifier = Modifier
//            .height(bannerHeight)
//            .wrapContentWidth()
//            .drawBehind {
//                val rectPartWidth = size.width - pointExtension.toPx()
//
//                val path = Path().apply {
//                    moveTo(0f, 0f)
//                    lineTo(rectPartWidth, 0f)
//                    lineTo(size.width, size.height / 2f)
//                    lineTo(rectPartWidth, size.height)
//                    lineTo(0f, size.height)
//                    close()
//                }
//                drawPath(path, color = backgroundColor)
//            }
//            .padding(start = textHorizontalPadding, end = textHorizontalPadding + pointExtension)
//    ) {
//        Text(
//            text = text,
//            color = Color.White,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun WhatWeDoSectionPreview() {
//    MaterialTheme {
//        whatwedo()
//    }
//}
//
//data class CapabilityItem(
//    val icon: ImageVector,
//    val title: String,
//    val description: String
//)
//
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
//@Composable
//fun OurCapabilitiesSection(capabilitiesViewModel: CapabilitiesViewModel = viewModel()) {
//    val capabilities by capabilitiesViewModel.capabilities.collectAsState()
//
//    val lightPinkBackground = Color(0xFFFFF9F7)
//    val orangeColor = Color(0xFFF9794D)
//    val darkTextColor = Color(0xFF333333)
//    val descriptionItemColor = Color(0xFF555555)
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
//                .background(Color(0xFFFBCFE8))
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.smileman),
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
//            items(capabilities) { capability ->
//                CapabilityRowItem(
//                    icon = capability.icon,
//                    title = capability.title,
//                    description = capability.description,
//                    iconBackgroundColor = orangeColor,
//                    titleColor = orangeColor,
//                    descriptionColor = descriptionItemColor
//                )
//            }
//        }
//    }
//}
//
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
//    val previewViewModel: CapabilitiesViewModel= viewModel()
//    ANTINGANGGURTheme {
//        OurCapabilitiesSection(capabilitiesViewModel = previewViewModel)
//    }
//}
