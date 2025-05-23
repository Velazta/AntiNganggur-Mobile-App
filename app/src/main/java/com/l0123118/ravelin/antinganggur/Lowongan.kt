package com.l0123118.ravelin.antinganggur

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.BorderStroke
import com.l0123118.ravelin.antinganggur.ui.theme.PrimaryOrange
import com.l0123118.ravelin.antinganggur.ui.theme.LightOrange
import com.l0123118.ravelin.antinganggur.ui.theme.BackgroundOrange
import com.l0123118.ravelin.antinganggur.ui.theme.LightPeach
import com.l0123118.ravelin.antinganggur.ui.theme.Gray
import com.l0123118.ravelin.antinganggur.ui.theme.LightGray

// data class lowongan
data class Job(
    val title: String,
    val company: String,
    val location: String,
    val type: String,
    val salary: String,
    val description: String,
    val iconResId: Int? = null
)

// viewModel buat mengelola data lowongan pekerjaan
class JobViewModel : androidx.lifecycle.ViewModel() {
    private val _trendingJobs = mutableStateListOf<Job>()
    val trendingJobs: List<Job> get() = _trendingJobs

    private val _recentJobs = mutableStateListOf<Job>()
    val recentJobs: List<Job> get() = _recentJobs

    init {
        //data buat lowongan trending
        _trendingJobs += Job(
            title = "Frontend Developer",
            company = "AntiNganggur",
            location = "Kota Surakarta",
            type = "Full-Time",
            salary = "Rp22-32jt/Bulan",
            description = "Bangun antarmuka web yang menarik dan mudah digunakan.",
            iconResId = R.drawable.frontend
        )
        _trendingJobs += Job(
            title = "Data Analyst",
            company = "AntiNganggur",
            location = "Jakarta Selatan",
            type = "Full-Time",
            salary = "Rp18-25jt/Bulan",
            description = "Menganalisis data untuk mendukung pengambilan keputusan bisnis.",
            iconResId = R.drawable.datascientist
        )
        _trendingJobs += Job(
            title = "Mobile Developer",
            company = "AntiNganggur",
            location = "Surabaya",
            type = "Full-Time",
            salary = "Rp20-30jt/Bulan",
            description = "Mengembangkan dan memelihara aplikasi mobile (Android/iOS).",
            iconResId = R.drawable.mobiledev
        )
        _trendingJobs += Job(
            title = "Cyber Security Analyst",
            company = "AntiNganggur",
            location = "Bandung",
            type = "Full-Time",
            salary = "Rp20-30jt/Bulan",
            description = "Melindungi sistem komputer dan jaringan dari ancaman siber.",
            iconResId = R.drawable.cyberanalyst
        )

        // Data untuk Lowongan Terbaru
        _recentJobs += Job(
            title = "Backend Developer",
            company = "AntiNganggur",
            location = "Bandung",
            type = "Full-Time",
            salary = "Rp20–30jt/Bulan",
            description = "Bangun dan kembangkan API serta sistem backend perusahaan. Tanggung jawab meliputi desain database, implementasi logika bisnis, dan integrasi layanan.",
            iconResId = R.drawable.backend
        )
        _recentJobs += Job(
            title = "DevOps Engineer",
            company = "AntiNganggur",
            location = "Jakarta",
            type = "Hybrid",
            salary = "Rp25–35jt/Bulan",
            description = "Kelola infrastruktur dan deployment pipeline dengan aman dan efisien. Fokus pada otomatisasi, pemantauan, dan peningkatan skalabilitas sistem.",
            iconResId = R.drawable.devops
        )
        _recentJobs += Job(
            title = "Cloud Architect",
            company = "AntiNganggur",
            location = "Jakarta",
            type = "Full-Time",
            salary = "Rp30–45jt/Bulan",
            description = "Merancang dan mengimplementasikan solusi cloud scalable di platform seperti AWS, Azure, atau GCP. Memastikan keamanan dan performa infrastruktur cloud.",
            iconResId = R.drawable.cloudarchitect
        )
        _recentJobs += Job(
            title = "Software Engineer",
            company = "AntiNganggur",
            location = "Yogyakarta",
            type = "Full-Time",
            salary = "Rp15-28jt/Bulan",
            description = "Merancang, mengembangkan, dan memelihara aplikasi perangkat lunak yang scalable dan efisien menggunakan bahasa pemrograman modern. Berkontribusi dalam seluruh siklus hidup pengembangan perangkat lunak.",
            iconResId = R.drawable.softwareengineer
        )
        _recentJobs += Job(
            title = "Network Administrator",
            company = "AntiNganggur",
            location = "Surabaya",
            type = "Full-Time",
            salary = "Rp10-18jt/Bulan",
            description = "Mengelola, mengkonfigurasi, dan memelihara infrastruktur jaringan perusahaan. Memastikan ketersediaan jaringan yang tinggi dan keamanan data.",
            iconResId = R.drawable.netadministrator
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LowonganScreen(viewModel: JobViewModel = viewModel()) {
    val trendingJobs = viewModel.trendingJobs
    val recentJobs = viewModel.recentJobs
    var keyword by remember { mutableStateOf("") }
    var locationFilter by remember { mutableStateOf("Semua Lokasi") }

//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Image(
//                            painter = painterResource(id = R.drawable.logoantinganggur),
//                            contentDescription = "Logo Anti Nganggur",
//                            modifier = Modifier.size(36.dp)
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text("Anti", color = Color.DarkGray, fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                        Text("Nganggur", color = PrimaryOrange, fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { /* TODO: Handle menu click */ }) {
//                        Image(
//                            painter = painterResource(id = R.drawable.menu),
//                            contentDescription = "Menu Icon",
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
//            )
//        }
//    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BackgroundOrange)
                        .padding(vertical = 32.dp, horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Daftarkan Pekerjaan Impian", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text("Anda Di AntiNganggur", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(modifier = Modifier.height(24.dp))
                        // Komponen pencarian
                        SearchLowonganSection(keyword, { keyword = it }, locationFilter, { locationFilter = it })
                    }
                }
            }

            // bagian lowongan yang trending
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightPeach)
                        .padding(vertical = 24.dp, horizontal = 16.dp)
                ) {
                    Text("Lowongan Kerja Yang", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Sedang Trending", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Temukan Lowongan Pekerjaan Teratas Yang Banyak", fontSize = 14.sp, color = Color.White)
                    Text("Dilamar Oleh Para Pencari Kerja.", fontSize = 14.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(24.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(end = 8.dp)
                    ) {
                        items(trendingJobs) { job ->
                            JobCard(job)
                        }
                    }
                }
            }

            // bagian lowongan yang terbaru
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightOrange)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    Text("Lowongan Terbaru", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Jangan lewatkan kesempatan emas, segera lamar lowongan terbaru ini!", fontSize = 14.sp, color = Gray)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // detail lowongan yang terbaru
            items(recentJobs) { job ->
                JobDetailCard(
                    job,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }

@Composable
fun SearchLowonganSection(
    keyword: String,
    onKeywordChange: (String) -> Unit,
    lokasi: String,
    onLokasiChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(LightPeach)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = keyword,
                onValueChange = onKeywordChange,
                placeholder = { Text("Posisi Pekerjaan") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Gray,
                    unfocusedBorderColor = LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = lokasi,
                onValueChange = onLokasiChange, // Tetap terima, meskipun readOnly
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Gray,
                    unfocusedBorderColor = LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.dropdown),
                        contentDescription = "dropdown lokasi",
                        modifier = Modifier.size(24.dp),
                        tint = Gray
                    )
                }
            )

            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Cari", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
        }
    }
}

// JobCard buat ringkasan lowongan trending
@Composable
fun JobCard(job: Job) {
    Card(
        modifier = Modifier.width(280.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                job.iconResId?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "${job.title} Icon",
                        tint = PrimaryOrange,
                        modifier = Modifier
                            .size(48.dp)
                            .background(LightOrange, RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(job.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(job.company, fontSize = 14.sp, color = Gray)
                    Text(job.location, fontSize = 12.sp, color = Gray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Kisaran Gaji", fontSize = 12.sp, color = Gray)
                    Text(job.salary, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }
                Column {
                    Text("Waktu Kerja", fontSize = 12.sp, color = Gray)
                    Text(job.type, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lamar", color = Color.White)
            }
        }
    }
}

//tampilan detail untuk lowongan terbaru
@Composable
fun JobDetailCard(job: Job, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            job.iconResId?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = "${job.title} Icon",
                    tint = PrimaryOrange,
                    modifier = Modifier
                        .size(48.dp)
                        .background(LightOrange, RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )
            }

            Text(job.title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            Text(job.company, fontSize = 14.sp, color = Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Location",
                    modifier = Modifier.size(16.dp),
                    tint = PrimaryOrange
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(job.location, fontSize = 12.sp, color = Gray)
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painter = painterResource(id = R.drawable.job),
                    contentDescription = "Job Type",
                    modifier = Modifier.size(16.dp),
                    tint = PrimaryOrange
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(job.type, fontSize = 12.sp, color = Gray)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.money),
                    contentDescription = "Salary Range",
                    modifier = Modifier.size(16.dp),
                    tint = PrimaryOrange
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(job.salary, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(job.description, maxLines = 3, overflow = TextOverflow.Ellipsis, fontSize = 14.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Lamar", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(
                    onClick = { /* TODO */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryOrange),
                    border = BorderStroke(1.dp, PrimaryOrange)
                ) {
                    Text("Lihat Detail")
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewLowonganScreenCombined() {
    MaterialTheme {
        Surface {
            LowonganScreen()
        }
    }
}