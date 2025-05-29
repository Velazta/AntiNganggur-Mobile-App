package com.l0123118.ravelin.antinganggur.menulist.mainpage


import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.navigation.Screen
import com.l0123118.ravelin.antinganggur.ui.theme.*
import com.l0123118.ravelin.antinganggur.ui.theme.LightPeach

@Composable
fun Home(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp, bottom = 16.dp) // Sesuaikan padding manual jika diperlukan
            .verticalScroll(scrollState)
            .background(LightPinkBackground)
    ) {
        WelcomeSection(navController = navController) // Section 1: Welcome
        JobSection(navController = navController) // Section 2: Lowongan IT
        FourstepSection() // Section 3: Mulai Perjalanan
        TestimonialSection() // Section 4: Apa Kata Profesional
    }
}

@Composable
fun WelcomeSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(LightOrange, LightPeach)
                ),
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(top = 32.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.welcomepic),
            contentDescription = "Welcome",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.Bold)) {
                    append("Selamat ")
                }
                withStyle(style = SpanStyle(color = PrimaryOrange, fontWeight = FontWeight.Bold)) {
                    append("Datang!")
                }
            },
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.Bold)) {
                    append("Temukan Pekerjaan ")
                }
                withStyle(style = SpanStyle(color = PrimaryOrange, fontWeight = FontWeight.Bold)) {
                    append("Impian Anda.")
                }
            },
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = "AntiNganggur membantu Anda menemukan pekerjaan yang sesuai dengan minat dan bakat Anda. Mulailah perjalanan karier Anda sekarang!",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {navController.navigate(Screen.LowonganScreen.route) {
                    popUpTo(Screen.Home.route) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                } },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                modifier = Modifier.border(
                    BorderStroke(2.dp, PrimaryOrange),
                    shape = RoundedCornerShape(8.dp)
                )
            ) {
                Text(
                    text = "Lihat Lowongan",
                    color = PrimaryOrange,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { /* TO DO */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                modifier = Modifier.border(
                    BorderStroke(2.dp, PrimaryOrange),
                    shape = RoundedCornerShape(8.dp)
                )
            ) {
                Text(
                    text = "Lamar",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun JobSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Lowongan IT Terbuka",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = OrangePrimary
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = "Temukan Pekerjaan Teknologi Terkini Dari Perusahaan AntiNganggur",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = TextColorSecondary
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .padding(bottom = 10.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))

        //Frontend Developer
        JobCard(
            title = "Frontend Developer",
            company = "Anti Nganggur",
            location = "Surakarta",
            jobType = "Full-Time",
            salary = "Rp15-25jt/Bulan",
            description = "Membutuhkan Frontend Developer Berpengalaman React.Js Untuk Membangun Antarmuka Web Yang Responsif, Menarik, Dan Mudah Digunakan. Kandidat Harus Memahami Integrasi API, Version Control, Dan Prinsip Desain UI Yang Baik.",
            iconResId = R.drawable.frontend
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Data Scientist
        JobCard(
            title = "Data Scientist",
            company = "Anti Nganggur",
            location = "Yogyakarta",
            jobType = "Full-Time",
            salary = "Rp18-30jt/Bulan",
            description = "Mencari Data Scientist yang mahir dalam analisis data, machine learning, dan membangun model prediktif untuk memecahkan masalah bisnis.",
            iconResId = R.drawable.datascientist
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Cyber Security Analyst
        JobCard(
            title = "Cyber Security Analyst",
            company = "Anti Nganggur",
            location = "Bandung",
            jobType = "Part-Time",
            salary = "Rp10-15jt/Bulan",
            description = "Mencari Cyber Security Analyst untuk memantau, mendeteksi, dan merespons ancaman keamanan siber, serta melindungi sistem dan data perusahaan.",
            iconResId = R.drawable.cyberanalyst
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {navController.navigate(Screen.LowonganScreen.route)},
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(2.dp, PrimaryOrange),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentPadding = PaddingValues(vertical = 6.dp)
        ) {
            Text(text = "Lihat Semua Lowongan", color = OrangePrimary, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun JobCard(
    title: String,
    company: String,
    location: String,
    jobType: String,
    salary: String,
    description: String,
    iconResId: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = iconResId), // Menggunakan iconResId yang diberikan
                    contentDescription = "Job Icon",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextColorPrimary
                        )
                    )
                    Text(
                        text = company,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextColorSecondary
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Location
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = LightGray)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.location), // Pastikan ini ada di drawable
                            contentDescription = "Location Icon",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = location,
                            style = MaterialTheme.typography.labelSmall.copy(color = TextColorSecondary)
                        )
                    }
                }

                // Job Type
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = LightGray)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.job), // Pastikan ini ada di drawable
                            contentDescription = "Job Type Icon",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = jobType,
                            style = MaterialTheme.typography.labelSmall.copy(color = TextColorSecondary)
                        )
                    }
                }

                // Salary
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = LightGray)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.money),
                            contentDescription = "Salary Icon",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = salary,
                            style = MaterialTheme.typography.labelSmall.copy(color = TextColorSecondary)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(color = TextColorPrimary),
                maxLines = 3,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /* TO DO */ },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(text = "Lamar", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { /* TO DO */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(PrimaryOrange, PrimaryOrange))),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(text = "Lihat Detail", color = PrimaryOrange, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun FourstepSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mulai Perjalanan Karier Mu",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = OrangePrimary
            )
        )
        Text(
            text = "Empat Langkah Mudah Menuju Karier Impian",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = TextColorSecondary
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                FourStepCard(
                    step = "1",
                    title = "Buat Profile",
                    description = "Buat profile lengkap berisi Dengan skill & pengalaman teknologi Anda"
                )
                FourStepCard(
                    step = "2",
                    title = "Temukan Pekerjaan",
                    description = "Cari lowongan IT yang cocok dengan bakat dan minat Anda."
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                FourStepCard(
                    step = "3",
                    title = "Lamar Pekerjaan",
                    description = "Kirim lamaran dan ikuti proses selanjutnya."
                )
                FourStepCard(
                    step = "4",
                    title = "Mulai Karir IT",
                    description = "Raih pekerjaan impian dan mulai karier teknologi Anda."
                )
            }
        }
    }
}

@Composable
fun FourStepCard(step: String, title: String, description: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(190.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(PrimaryOrange),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = step,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextColorPrimary
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextColorSecondary
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun TestimonialSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Apa Kata Profesional",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = PrimaryOrange
            )
        )
        Text(
            text = "Tentang AntiNganggur",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = PrimaryOrange
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            TestimonialCard(
                name = "Tony Maulana",
                role = "UI/UX Designer",
                testimonial = "Berkat AntiNganggur, Saya Berhasil Mendapatkan posisi sebagai UI/UX Designer di Startup Teknologi Ternama. Dengan fitur pencarian yang mudah dan rekomendasi pekerjaan yang relevan, AntiNganggur adalah platform yang sangat membantu!"
            )
            TestimonialCard(
                name = "Budi Santoso",
                role = "Mobile Developer",
                testimonial = "AntiNganggur sangat membantu saya menemukan pekerjaan Mobile Developer yang sesuai dengan kualifikasi saya. Proses lamarannya pun mudah dan cepat. Sangat direkomendasikan untuk para pencari kerja IT!"
            )
            TestimonialCard(
                name = "Siti Rahayu",
                role = "Data Scientist",
                testimonial = "Sebagai seorang Data Scientist, saya menemukan banyak peluang menarik di AntiNganggur. Fitur filter yang lengkap sangat membantu saya menyaring lowongan yang paling relevan. Terima kasih AntiNganggur!"
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun TestimonialCard(name: String, role: String, testimonial: String) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(PrimaryOrange),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name.first().toString(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextColorPrimary
                        )
                    )
                    Text(
                        text = role,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextColorSecondary
                        )
                    )
                }
            }
            Text(
                text = "\"$testimonial\"",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextColorPrimary
                ),
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 4
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    ANTINGANGGURTheme {
        Home(navController = rememberNavController())
    }
}