package com.l0123118.ravelin.antinganggur

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Send
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
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
import com.l0123118.ravelin.antinganggur.ui.theme.BackgroundOrange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.String

// Definisikan warna yang digunakan
val OrangeAAG = Color(0xFFFF7A59)
val TextWhiteAAG = Color.White
val BackgroundImageAAG = Color(0xFFFFF6F4) // Background untuk area gambar
val ButtonBorderAAG = Color.White


class AboutUS : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANTINGANGGURTheme {
                AboutUsPage(navController = rememberNavController())
            }
        }
    }
}

@Composable
fun AboutUsPage(navController: NavController) {

}

@Composable
fun latarBelakang() {
    val orangeColor = Color(0xFFF9794D) // Warna oranye dari gambar (approximated)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Agar bisa di-scroll jika konten terlalu panjang
            .background(BackgroundImageAAG) // Background default untuk area di luar section
    ) {
        // Bagian Oranye dengan teks dan tombol
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight() // Tinggi box akan menyesuaikan konten di dalamnya
                .drawBehind {
                    val path = Path().apply {
                        moveTo(0f, 0f) // Mulai dari kiri atas
                        lineTo(size.width, 0f) // Garis ke kanan atas
                        lineTo(size.width, size.height * 0.80f) // Garis lurus ke bawah di sisi kanan (80% tinggi)

                        // Kurva melengkung ke bawah di tengah
                        quadraticBezierTo(
                            x1 = size.width / 2f, // Titik kontrol X di tengah
                            y1 = size.height * 1.15f, // Titik kontrol Y di bawah batas bawah box (untuk efek melengkung ke bawah)
                            x2 = 0f, // Titik akhir X di kiri
                            y2 = size.height * 0.80f // Titik akhir Y di sisi kiri (80% tinggi)
                        )
                        close() // Menutup path kembali ke titik awal (implisit ke moveTo jika tidak ada lineTo lagi)
                    }
                    drawPath(
                        path = path,
                        color = orangeColor
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    // Padding vertikal disesuaikan agar teks tidak terlalu dekat dengan lengkungan
                    .padding(top = 40.dp, bottom = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Selamat Datang Di Perusahaan Kami",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    lineHeight = 38.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "AntiNganggur didirikan pada tahun 2020 oleh sekelompok profesional IT yang melihat adanya kesenjangan antara kebutuhan industri dan keterampilan lulusan teknologi di Indonesia.",
                    color = Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Berawal dari komunitas kecil yang berbagi pengetahuan secara online, kami berkembang menjadi platform komprehensif yang menghubungkan talenta IT dengan peluang karir dan pembelajaran yang relevan dengan kebutuhan industri.",
                    color = Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Saat ini, AntiNganggur telah membantu lebih dari 5.000 profesional IT menemukan pekerjaan impian mereka dan membekali lebih dari 15.000 pembelajar dengan keterampilan digital yang dibutuhkan di era modern.",
                    color = Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedButton(
                    onClick = { /* Aksi ketika tombol diklik */ },
                    shape = RoundedCornerShape(50), // Membuat tombol sangat rounded
                    border = BorderStroke(1.5.dp, Color.White),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.Transparent // Latar belakang tombol transparan
                    ),
                    modifier = Modifier.defaultMinSize(minWidth = 180.dp, minHeight = 48.dp)
                ) {
                    Text(
                        "Lihat Detail",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Bagian Ilustrasi
        // Ganti R.drawable.company_background_full dengan resource ID gambar Anda
        // Gambar input_file_0.png (yang Anda berikan) berisi SELURUH tampilan.
        // Jika Anda ingin hanya bagian ilustrasi, Anda perlu memotongnya atau menggunakan
        // gambar yang hanya berisi ilustrasi.
        // Kode di bawah akan mencoba menampilkan bagian bawah dari input_file_0.png
        // dengan asumsi Anda menyimpannya sebagai 'company_background_full.xml' di drawable.
        // Ini adalah cara untuk mencoba "memotong" secara visual.
        // Cara terbaik adalah memiliki file gambar terpisah hanya untuk ilustrasi.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Sesuaikan tinggi ilustrasi yang diinginkan
                .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp)) // Untuk memastikan tidak ada artefak clipping aneh
        ) {
            Image(
                // GANTI 'R.drawable.company_background_full' dengan nama file yang benar
                // setelah Anda menambahkannya ke folder res/drawable.
                // Contoh: Jika Anda menamai file 'input_file_0.png' menjadi 'company_hero.png' di drawable,
                // maka gunakan painterResource(id = R.drawable.company_hero)
                painter = painterResource(id = R.drawable.ilustrationaboutpage), // Placeholder, ganti ini!
                contentDescription = "Ilustrasi Perusahaan",
                contentScale = ContentScale.Crop, // Akan memotong gambar agar sesuai & mengisi
                alignment = Alignment.BottomCenter, // Fokus pada bagian bawah gambar jika di-crop
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun latabelakangPreview() {
    ANTINGANGGURTheme { // Pastikan Anda memiliki tema Material3 yang disetel di aplikasi Anda
        latarBelakang()
    }
}


@Composable
fun whatwedo() {
    val imageGradientColors = listOf(Color(0xFFFFF6F4), Color(0xFFFFF6F4)) // Gradasi pink muda untuk bg gambar
    val descriptionBackgroundColor = Color(0xFFFFF6F4) // Pink sangat muda untuk bg deskripsi
    val bannerOrangeColor = Color(0xFFF9794D) // Oranye untuk banner

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(descriptionBackgroundColor) // Background utama (area teks juga warna ini)
    ) {
        // 1. Area Gambar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = imageGradientColors,
                        start = Offset.Zero, // Top-left
                        end = Offset.Infinite // Bottom-right
                    )
                )
        ) {
            Image(
                // GANTI DENGAN RESOURCE GAMBAR ANDA (misal: R.drawable.what_we_do_image)
                painter = painterResource(id = R.drawable.whatwedobanner),
                contentDescription = "What We Do Illustration",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 50.dp) // Padding untuk gambar
                    .aspectRatio(4f / 3f) // Sesuaikan aspect ratio gambar Anda
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        // 2. Banner "What We Do" dan Deskripsi
        Column(
            modifier = Modifier
                .fillMaxWidth()
            // Background sudah diatur oleh Column luar
            // .background(descriptionBackgroundColor) // Tidak perlu jika Column luar sudah set
        ) {
            // Box untuk menampung banner dan memberikan offset
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Agar padding horizontal efektif
                    .offset(y = (-28).dp) // Tarik banner ke atas (setengah tinggi banner umum + sedikit)
                    .padding(horizontal = 20.dp) // Padding horizontal untuk banner dari tepi layar
            ) {
                WhatWeDoBannerComposable(
                    backgroundColor = bannerOrangeColor,
                    text = "What We Do"
                )
            }

            // Spacer untuk memberi jarak setelah banner yang di-offset
            // Jika banner tinggi 48dp dan offset -28dp, secara visual ada ruang kosong.
            // Teks di bawahnya perlu padding dari atas Column ini.
            // Karena offset tidak mengubah ruang layout, kita hanya perlu padding standar.
            // Namun, karena banner ditarik ke atas, kita perlu sedikit padding tambahan agar teks tidak terlalu mepet.
            // Spacer(modifier = Modifier.height(4.dp)) // Sedikit ruang tambahan jika perlu

            Text(
                text = "AntiNganggur.id didirikan untuk menjadi solusi digital dalam mengatasi permasalahan pengangguran di sektor IT. Kami menyediakan platform yang menghubungkan perusahaan dengan kandidat berkualitas, menawarkan informasi yang akurat dan relevan tentang lowongan pekerjaan serta profil perusahaan. Dengan pendekatan yang proaktif, kami berkomitmen untuk memberdayakan pencari kerja dan membantu mereka menemukan peluang karir yang tepat. Hingga saat ini, AntiNganggur.id telah menjembatani ribuan profesional IT dengan peluang kerja yang sesuai, berkontribusi pada pertumbuhan industri teknologi informasi di Indonesia.",
                color = Color.Black, // Abu-abu gelap
                fontSize = 15.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
                // Padding top di sini adalah dari posisi layout normal banner Box,
                // bukan dari posisi visual banner yang sudah di-offset.
                // Jadi, jika banner tidak di-offset, ini adalah jarak dari banner.
                // Jika banner di-offset naik, teks akan tampak lebih dekat ke banner secara visual.
                // Tidak perlu top padding besar di sini karena offset banner sudah "menciptakan" ruang.
            )
        }
    }
}

@Composable
fun WhatWeDoBannerComposable(backgroundColor: Color, text: String) {
    val bannerHeight = 48.dp // Tinggi banner
    // Lebar bagian runcing, bisa disesuaikan. Sekitar 0.4x tinggi banner.
    val pointExtension = bannerHeight * 0.4f
    // Padding horizontal untuk teks di dalam banner.
    val textHorizontalPadding = 20.dp

    Box(
        contentAlignment = Alignment.CenterStart, // Teks rata kiri tengah di dalam Box
        modifier = Modifier
            .height(bannerHeight)
            .wrapContentWidth() // Lebar Box akan menyesuaikan teks + padding untuk ujung runcing
            .drawBehind {
                // size.width adalah total lebar Box (lebar teks + padding awal + pointExtension)
                // size.height adalah bannerHeight
                val rectPartWidth = size.width - pointExtension.toPx()

                val path = Path().apply {
                    moveTo(0f, 0f) // Kiri atas
                    lineTo(rectPartWidth, 0f) // Kanan atas bagian persegi
                    lineTo(size.width, size.height / 2f) // Ujung runcing
                    lineTo(rectPartWidth, size.height) // Kanan bawah bagian persegi
                    lineTo(0f, size.height) // Kiri bawah
                    close()
                }
                drawPath(path, color = backgroundColor)
            }
            // Padding ini penting:
            // Memberi ruang di kiri untuk teks (textHorizontalPadding).
            // Memberi ruang di kanan untuk teks (textHorizontalPadding) DAN ujung runcing (pointExtension).
            // DrawBehind akan menggunakan total lebar yang dihasilkan oleh padding ini.
            .padding(start = textHorizontalPadding, end = textHorizontalPadding + pointExtension)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 20.sp, // Ukuran font untuk "What We Do"
            fontWeight = FontWeight.Bold
            // Tidak perlu padding tambahan pada Text, karena sudah diatur oleh parent Box.
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WhatWeDoSectionPreview() {
    MaterialTheme { // Pastikan Anda memiliki tema Material3
        // Untuk preview, kita bisa menyediakan drawable placeholder jika 'input_file_0' tidak ada di context preview
        // Namun, jika Anda sudah menambahkan 'input_file_0.png' ke drawable, ini akan bekerja.
        whatwedo()
    }
}

// 1. Data Class
data class CapabilityItem(
    val icon: ImageVector, // Type is ImageVector
    val title: String,
    val description: String
)

// 2. ViewModel
class CapabilitiesViewModel : ViewModel() {
    private val _capabilities = MutableStateFlow<List<CapabilityItem>>(emptyList())
    val capabilities: StateFlow<List<CapabilityItem>> = _capabilities.asStateFlow()

    init {
        loadCapabilities()
    }

    private fun loadCapabilities() {
        _capabilities.value = listOf(
            CapabilityItem(
                icon = Icons.Filled.Settings,
                title = "Pengembangan Solusi IT Kustom",
                description = "mengembangkan solusi perangkat lunak yang disesuaikan dengan kebutuhan spesifik klien, mulai dari aplikasi mobile hingga sistem manajemen data"
            ),
            CapabilityItem(
                icon = Icons.Filled.CloudUpload,
                title = "Integrasi Teknologi Modern",
                description = "Kami mengintegrasikan teknologi modern seperti AI, Big Data, dan Cloud Computing untuk memberikan solusi yang efisien dan inovatif."
            ),
            CapabilityItem(
                icon = Icons.Filled.Security,
                title = "Keamanan Cyber",
                description = "Kami menawarkan layanan keamanan siber yang komprehensif untuk melindungi data dan infrastruktur IT Anda dari ancaman siber yang terus berkembang."
            ),
            CapabilityItem(
                icon = Icons.Filled.Settings,
                title = "Dukungan dan Pemeliharaan IT",
                description = "Kami menyediakan layanan dukungan teknis dan pemeliharaan sistem berkelanjutan, memastikan infrastruktur IT klien berfungsi optimal dan selalu diperbarui dengan teknologi terbaru."
            ),

        )
    }
}

// 3. Main Composable Section
@Composable
fun OurCapabilitiesSection(capabilitiesViewModel: CapabilitiesViewModel = viewModel()) {
    val capabilities by capabilitiesViewModel.capabilities.collectAsState()

    val lightPinkBackground = Color(0xFFFFF9F7)
    val orangeColor = Color(0xFFF9794D)
    val darkTextColor = Color(0xFF333333) // Color for "We Provide"
    val descriptionItemColor = Color(0xFF555555) // Color for capability description text

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightPinkBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color(0xFFFBCFE8)) // Pinkish background for hero image area
        ) {
            Image(
                // Ensure 'smileman.png' (originally input_file_0.png) is in your res/drawable folder
                painter = painterResource(id = R.drawable.smileman), // Using your specified R.drawable.smileman
                contentDescription = "Our Capabilities Hero",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            )
        }

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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(capabilities) { capability -> // 'capability' is of type CapabilityItem
                CapabilityRowItem(
                    icon = capability.icon, // Should resolve to ImageVector
                    title = capability.title, // Should resolve to String
                    description = capability.description, // Should resolve to String
                    iconBackgroundColor = orangeColor,
                    titleColor = orangeColor, // Title text color for the item
                    descriptionColor = descriptionItemColor // Description text color for the item
                )
            }
        }
    }
}

// 4. Composable for each row item in LazyColumn
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
            Text(
                text = title,
                color = titleColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = description,
                color = descriptionColor,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
fun OurCapabilitiesSectionPreview() {
    // Deklarasikan instance ViewModel di sini untuk digunakan oleh preview
    val previewViewModel: CapabilitiesViewModel= viewModel()

    // Pastikan ANTINGANGGURTheme adalah tema Material (M2 atau M3) aplikasi Anda
    // dan sudah diimpor dengan benar jika berada di file lain.
    ANTINGANGGURTheme {
        OurCapabilitiesSection(capabilitiesViewModel = previewViewModel)
    }
}

// Warna berdasarkan input_file_0.png dan interpretasi
val VisMisOrangeBanner = Color(0xFFF9794D)
val VisMisCardBorderColor = VisMisOrangeBanner
val VisMisCardContentBgColor = Color.White
val VisMisSectionBgColor = Color(0xFFFFFBF9) // Latar belakang section (krem/pink muda)
val VisMisSubtitleTextColor = Color(0xFF5A5A5A) // Abu-abu untuk subtitle
val VisMisIconBgColor = Color(0xFFFFEAE4) // Latar belakang area ikon (pink/oranye muda)
val VisMisIconTintColor = VisMisOrangeBanner // Warna ikon petir
val VisMisTitleTextColor = VisMisOrangeBanner // Warna teks "VISI" / "MISI"
val VisMisVisionTextColor = Color(0xFF8C5B4C) // Warna teks visi (coklat/oranye tua)
val VisMisMissionTextColor = Color(0xFF4A4A4A) // Warna teks misi (abu-abu tua)

@Composable
fun VisiMisiSectionNew() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(VisMisSectionBgColor)
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Banner Judul "VISI MISI"
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .background(VisMisOrangeBanner)
                .padding(horizontal = 32.dp, vertical = 8.dp)
        ) {
            Text(
                text = "VISI MISI",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Teks Subtitle
        Text(
            text = "Kami berkomitmen untuk menciptakan dampak\npositif dalam ekosistem teknologi Indonesia",
            color = VisMisSubtitleTextColor,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // LazyRow untuk Kartu Visi dan Misi
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tentukan tinggi yang seragam untuk kedua kartu
            // Sesuaikan nilai ini agar pas dengan konten terpanjang dari kartu Visi atau Misi Anda.
            // Dari gambar yang baru, sepertinya kartu Visi lebih panjang.
            val uniformCardHeight = 370.dp // Misalnya, atau sesuaikan lagi

            item {
                VisionCardNew(
                    modifier = Modifier
                        .width(320.dp)
                        .height(uniformCardHeight) // Terapkan tinggi yang seragam
                )
            }
            item {
                MissionCardNew(
                    modifier = Modifier
                        .width(320.dp)
                        .height(uniformCardHeight) // Terapkan tinggi yang seragam
                )
            }
        }
    }
}

@Composable
fun CardShell(
    modifier: Modifier = Modifier,
    borderColor: Color = VisMisCardBorderColor,
    contentBackgroundColor: Color = VisMisCardContentBgColor,
    cornerRadius: Dp = 20.dp, // Sudut lebih membulat seperti di gambar
    borderWidth: Dp = 1.5.dp,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = RoundedCornerShape(cornerRadius), clip = false)
            .border(BorderStroke(borderWidth, borderColor), RoundedCornerShape(cornerRadius))
            .background(contentBackgroundColor, RoundedCornerShape(cornerRadius))
            .clip(RoundedCornerShape(cornerRadius)) // Pastikan konten juga ter-clip
    ) {
        content()
    }
}

@Composable
fun VisionCardNew(modifier: Modifier = Modifier) {
    CardShell(modifier = modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp) // Padding internal kartu
        ) {
            val (iconContainer, title, visionText) = createRefs()

            // Area Ikon
            Box(
                modifier = Modifier
                    .size(48.dp) // Ukuran area ikon
                    .clip(RoundedCornerShape(12.dp)) // Sudut membulat untuk latar belakang ikon
                    .background(VisMisIconBgColor)
                    .constrainAs(iconContainer) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Bolt, // Pastikan Icons.Filled.Bolt diimpor
                    contentDescription = "Visi Icon",
                    tint = VisMisIconTintColor,
                    modifier = Modifier.size(28.dp) // Ukuran ikon petir
                )
            }

            // Teks Judul "VISI"
            Text(
                text = "VISI",
                color = VisMisTitleTextColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(iconContainer.top)
                    bottom.linkTo(iconContainer.bottom)
                    start.linkTo(iconContainer.end, margin = 12.dp)
                }
            )

            // Teks Isi Visi
            Text(
                text = "Menjadi katalisator utama dalam menciptakan ekosistem teknologi Indonesia yang inklusif, inovatif, dan berkelanjutan, di mana setiap talenta digital dapat berkembang dan berkontribusi pada kemajuan bangsa.",
                color = VisMisVisionTextColor,
                fontSize = 15.sp,
                lineHeight = 23.sp,
                textAlign = TextAlign.Start, // <<< PENYESUAIAN UTAMA: Memastikan perataan kiri
                modifier = Modifier.constrainAs(visionText) {
                    top.linkTo(iconContainer.bottom, margin = 20.dp)
                    start.linkTo(parent.start) // Teks dimulai dari sisi kiri parent (setelah padding kartu)
                    end.linkTo(parent.end)     // Teks bisa melebar hingga sisi kanan parent (untuk wrapping)
                    bottom.linkTo(parent.bottom, goneMargin = 16.dp) // Memberi ruang di bawah jika tidak ada elemen lain
                    width = Dimension.fillToConstraints // Mengisi lebar sesuai constraint
                    height = Dimension.preferredWrapContent // Tinggi menyesuaikan konten teks Visi
                }
            )
        }
    }
}

@Composable
fun MissionCardNew(modifier: Modifier = Modifier) {
    val missionItems = listOf(
        "Menyediakan platform pembelajaran yang relevan dengan kebutuhan industri teknologi terkini",
        "Menyediakan platform pembelajaran yang relevan dengan kebutuhan industri teknologi terkini",
        "Menyediakan platform pembelajaran yang relevan dengan kebutuhan industri teknologi terkini"
    )

    CardShell(modifier = modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            val (iconContainer, title, missionList) = createRefs()

            // Area Ikon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(VisMisIconBgColor)
                    .constrainAs(iconContainer) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Bolt,
                    contentDescription = "Misi Icon",
                    tint = VisMisIconTintColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Teks Judul "MISI"
            Text(
                text = "MISI",
                color = VisMisTitleTextColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(iconContainer.top)
                    bottom.linkTo(iconContainer.bottom)
                    start.linkTo(iconContainer.end, margin = 12.dp)
                }
            )

            // Daftar Poin Misi
            Column(
                modifier = Modifier.constrainAs(missionList) {
                    top.linkTo(iconContainer.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, goneMargin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.preferredWrapContent // Tinggi kolom menyesuaikan item misi
                }
            ) {
                missionItems.forEach { missionText ->
                    MissionListItem(text = missionText)
                    Spacer(modifier = Modifier.height(12.dp)) // Jarak antar item misi
                }
            }
        }
    }
}

@Composable
fun MissionListItem(text: String, icon: ImageVector = Icons.Filled.ChevronRight) {
    Row(verticalAlignment = Alignment.Top) { // Alignment.Top agar ikon sejajar dengan baris pertama teks
        Icon(
            imageVector = icon,
            contentDescription = "Mission bullet point",
            tint = VisMisIconTintColor, // Warna ikon panah (oranye)
            modifier = Modifier.size(20.dp).padding(top = 3.dp) // Sesuaikan ukuran dan padding agar visualnya pas
        )
        Spacer(modifier = Modifier.width(10.dp)) // Jarak antara ikon dan teks
        Text(
            text = text,
            color = VisMisMissionTextColor,
            fontSize = 15.sp,
            lineHeight = 23.sp
        )
    }
}


// Previews
@Preview(showBackground = true, backgroundColor = 0xFFFFFBF9) // Set background color for preview
@Composable
fun VisiMisiSectionNewPreview() {
    MaterialTheme { // Atau tema kustom Anda seperti ANTINGANGGURTheme
        VisiMisiSectionNew()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFBF9, widthDp = 350, heightDp = 320)
@Composable
fun VisionCardNewPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) { // Padding untuk melihat shadow kartu
            VisionCardNew(modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFBF9, widthDp = 350, heightDp = 370)
@Composable
fun MissionCardNewPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MissionCardNew(modifier = Modifier.fillMaxSize())
        }
    }
}

data class PersonilTeamItem(
    val bannerBackground: ImageVector,
    val personPictureId: Int? = null,
    val position: String,
    val onClick: () -> Unit
)

class PersonilTeamViewModel : ViewModel() {
    private val _personilteam = MutableStateFlow<List<PersonilTeamItem>>(emptyList())
    val personilteam: StateFlow<List<PersonilTeamItem>> = _personilteam.asStateFlow()

    init {
        loadPersonilTeam()
    }

    private fun loadPersonilTeam() {

        //Orang Pertama
        _personilteam.value = listOf(
            PersonilTeamItem(
                bannerBackground = Icons.Filled.Person,
                personPictureId  = R.drawable.smileman,
                position = "Manager",
                onClick = { /* Text Onclick For Detail Team Project*/}
            )
        )
        //Orang Kedua
        _personilteam.value = listOf(
            PersonilTeamItem(
                bannerBackground = Icons.Filled.Person,
                personPictureId  = R.drawable.smileman,
                position = "Manager",
                onClick = { /* Text Onclick For Detail Team Project*/}
            )
        )
        //Orang Ketiga
        _personilteam.value = listOf(
            PersonilTeamItem(
                bannerBackground = Icons.Filled.Person,
                personPictureId  = R.drawable.smileman,
                position = "Manager",
                onClick = { /* Text Onclick For Detail Team Project*/}
            )
        )
        //Orang Keempat
        _personilteam.value = listOf(
            PersonilTeamItem(
                bannerBackground = Icons.Filled.Person,
                personPictureId  = R.drawable.smileman,
                position = "Manager",
                onClick = { /* Text Onclick For Detail Team Project*/}
            )
        )

    }

}


//lanjut nanti capeeeeeeeeeeeeeeeeee
@Composable
fun PersonilTeam() {

}

@Preview(showBackground = true, heightDp = 800, widthDp = 320)
@Composable
fun PersonilTeamPreview() {
    MaterialTheme {
        PersonilTeam()
    }
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ANTINGANGGURTheme { // Pastikan Anda memiliki tema Material3 yang disetel di aplikasi Anda
//        latarBelakang()
//        Spacer(modifier = Modifier.height(20.dp))
//    }
//}

