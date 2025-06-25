package com.l0123118.ravelin.antinganggur.menulist.lowonganpage

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
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.navigation.Screen
import com.l0123118.ravelin.antinganggur.ui.theme.PrimaryOrange
import com.l0123118.ravelin.antinganggur.ui.theme.LightOrange
import com.l0123118.ravelin.antinganggur.ui.theme.BackgroundOrange
import com.l0123118.ravelin.antinganggur.ui.theme.LightPeach
import com.l0123118.ravelin.antinganggur.ui.theme.Gray
import com.l0123118.ravelin.antinganggur.ui.theme.LightGray
import android.util.Log

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
class JobViewModel : ViewModel() {
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
            title = "Data Scientist",
            company = "AntiNganggur",
            location = "Yogyakarta",
            type = "Full-Time",
            salary = "Rp18-30jt/Bulan",
            description = "Mencari Data Scientist yang mahir dalam analisis data, machine learning, dan membangun model prediktif untuk memecahkan masalah bisnis.",
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
        _recentJobs += Job(
            title = "Frontend Developer WFH",
            company = "AntiNganggur",
            location = "Jakarta",
            type = "Full-Time • Remote",
            salary = "Rp18-28jt/Bulan",
            description = "Mengembangkan antarmuka web responsif menggunakan React.js dan Vue.js. Bekerja secara remote dengan tim yang tersebar di seluruh Indonesia. Membutuhkan pengalaman minimal 2 tahun dalam frontend development.",
            iconResId = R.drawable.frontend
        )
        _recentJobs += Job(
            title = "Backend Developer WFH",
            company = "AntiNganggur",
            location = "Surabaya",
            type = "Full-Time • Remote",
            salary = "Rp20-32jt/Bulan",
            description = "Membangun dan memelihara API serta sistem backend menggunakan Node.js, Python, atau Java. Posisi full remote dengan fleksibilitas waktu kerja. Pengalaman dengan database dan cloud services diperlukan.",
            iconResId = R.drawable.backend
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLowonganSection(
    keyword: String,
    onKeywordChange: (String) -> Unit,
    lokasi: String,
    onLokasiChange: (String) -> Unit,
    onSearch: () -> Unit
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

            var expanded by remember { mutableStateOf(false) }
            val locations = listOf("Semua Lokasi", "Jakarta", "Surabaya", "Bandung", "Yogyakarta", "Surakarta")
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = lokasi,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
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
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    locations.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                onLokasiChange(selectionOption)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = onSearch,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Cari", color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LowonganScreen(
    navController: NavController = rememberNavController(),
    viewModel: JobViewModel = viewModel()
) {
    val trendingJobs = viewModel.trendingJobs
    val recentJobs = viewModel.recentJobs
    var keyword by remember { mutableStateOf("") }
    var locationFilter by remember { mutableStateOf("Semua Lokasi") }

    var filteredTrendingJobs by remember { mutableStateOf(trendingJobs) }
    var filteredRecentJobs by remember { mutableStateOf(recentJobs) }

    fun doSearch() {
        filteredTrendingJobs = trendingJobs
        filteredRecentJobs = recentJobs.filter { job ->
            (locationFilter == "Semua Lokasi" || job.location.contains(locationFilter, ignoreCase = true)) &&
                    (keyword.isBlank() || job.title.contains(keyword, ignoreCase = true))
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightOrange)
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
                    // Pass doSearch to SearchLowonganSection
                    SearchLowonganSection(
                        keyword, { keyword = it },
                        locationFilter, { locationFilter = it },
                        onSearch = { doSearch() }
                    )
                }
            }
        }

        // Trending jobs section
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
                    items(filteredTrendingJobs) { job ->
                        JobCard(job, navController)
                    }
                }
            }
        }

        // Recent jobs section
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

        // Recent jobs detail
        items(filteredRecentJobs) { job ->
            JobDetailCard(
                job,
                navController = navController,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

// JobCard buat ringkasan lowongan trending
@Composable
fun JobCard(job: Job, navController: NavController) {
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
                onClick = {
                    Log.d("JobNavigation", "Navigating to job detail: ${job.title}")
                    navController.navigate(Screen.JobDetail.createRoute(job.title))
                },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lamar", color = Color.White)
            }
        }
    }
}

//tampilan detail buat lowongan terbaru
@Composable
fun JobDetailCard(job: Job, navController: NavController, modifier: Modifier = Modifier) {
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        Log.d("JobNavigation", "Navigating to job detail: ${job.title}")
                        navController.navigate(Screen.JobDetail.createRoute(job.title))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.width(110.dp)
                ) {
                    Text("Lamar", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(
                    onClick = {
                        Log.d("JobNavigation", "Navigating to job detail: ${job.title}")
                        navController.navigate(Screen.JobDetail.createRoute(job.title))
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryOrange),
                    border = BorderStroke(1.dp, PrimaryOrange),
                ) {
                    Text("Lihat Detail", fontWeight = FontWeight.Bold)
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
