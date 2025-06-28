package com.l0123118.ravelin.antinganggur.menulist.lowonganpage

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
import android.util.Log
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.menulist.aboutuspage.VisiMisiSectionNew
import com.l0123118.ravelin.antinganggur.ui.theme.Gray
import com.l0123118.ravelin.antinganggur.ui.theme.LightOrange
import com.l0123118.ravelin.antinganggur.ui.theme.LightGray
import java.net.URLDecoder
import com.l0123118.ravelin.antinganggur.menulist.lowonganpage.Job
import androidx.compose.ui.platform.LocalContext
import com.l0123118.ravelin.antinganggur.MyApplication
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailScreen(
    navController: NavController,
    jobId: String? = null
) {
    val context = LocalContext.current
    var job by remember { mutableStateOf<Job?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(jobId) {
        Log.d("JobDetailScreen", "Received jobId = $jobId")
        job = getJobById(jobId, context)
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Lowongan") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = LightOrange)
            )
        }
    ) { paddingValues ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            job != null -> {
                JobDetailContent(job = job!!, paddingValues = paddingValues)
            }
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Lowongan tidak ditemukan")
                }
            }
        }
    }
}

@Composable
fun JobDetailContent(job: Job, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightOrange)
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        // Job header card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF8F9FA))
                            .border(1.dp, Color(0xFFE9ECEF), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        job.iconResId?.let {
                            Image(
                                painter = painterResource(id = it),
                                contentDescription = "Company Logo",
                                modifier = Modifier.size(40.dp),
                                contentScale = ContentScale.Fit
                            )
                        } ?: run {
                            Image(
                                painter = painterResource(id = R.drawable.logoantinganggur),
                                contentDescription = "Default Logo",
                                modifier = Modifier.size(40.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Job title and company
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = job.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = job.company,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFF6B7280)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Job details chips
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        listOf(
                            Triple(R.drawable.location, job.location, LightGray),
                            Triple(R.drawable.job, job.type, LightGray),
                            Triple(R.drawable.money, job.salary, LightGray)
                        )
                    ) { (icon, text, bgColor) ->
                        JobDetailChip(
                            icon = icon,
                            text = text,
                            backgroundColor = bgColor,
                            iconColor = OrangePrimary,
                            textColor = Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        // Requirements section
        SectionCard(title = "Persyaratan") {
            RequirementSection(job)
        }

        // Skills section
        SectionCard(title = "Keahlian yang Dibutuhkan") {
            Column {
                val skills = listOf("Administrasi", "Adaptasi Cepat").plus(
                    when {
                        job.title.contains("Developer") || job.title.contains("Engineer") -> listOf("Coding")
                        job.title.contains("Data") -> listOf("Analisis Data")
                        else -> emptyList()
                    }
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(skills) { skill ->
                        SkillChip(skill = skill, iconColor = OrangePrimary, textColor = Gray)
                    }
                }
            }
        }

        // Job description section
        SectionCard(title = "Deskripsi Pekerjaan") {
            Column {
                Text(
                    text = "Informasi umum pekerjaan:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF374151)
                )

                Spacer(modifier = Modifier.height(12.dp))

                val descriptionPoints = generateJobDescriptionPoints(job)
                descriptionPoints.forEachIndexed { index, point ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "${index + 1}.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OrangePrimary,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(end = 8.dp, top = 2.dp)
                        )
                        Text(
                            text = point,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF374151),
                            lineHeight = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Benefits section
        SectionCard(title = "Benefit") {
            Column {
                val benefits = generateBenefits(job)
                benefits.forEach { benefit ->
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .padding(top = 2.dp), // Adjust vertical alignment
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(OrangePrimary)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = benefit,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF374151),
                            lineHeight = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }                }
            }
        }

        // Working hours section with Apply button
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Jadwal Kerja",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WorkingHoursItem(
                        title = "Hari Kerja",
                        value = if (job.type.contains("Part")) "Senin - Jumat" else "Senin - Minggu"
                    )
                    WorkingHoursItem(
                        title = "Jam Kerja",
                        value = if (job.type.contains("Part")) "09:00 - 15:00" else "08:00 - 17:00"
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { /* TODO: Handle apply action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Text(
                        text = "LAMAR SEKARANG",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

@Composable
fun JobDetailChip(
    icon: Int,
    text: String,
    backgroundColor: Color,
    iconColor: Color,
    textColor: Color
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = backgroundColor,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = textColor,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun WorkingHoursItem(
    title: String,
    value: String
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF6B7280),
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A1A)
        )
    }
}

@Composable
fun RequirementSection(job: Job) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RequirementItem(
                icon = Icons.Outlined.School,
                title = "Pendidikan",
                value = "Minimal S1",
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            RequirementItem(
                icon = Icons.Outlined.Person,
                title = "Jenis Kelamin",
                value = "Tidak ada ketentuan",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RequirementItem(
                icon = Icons.Outlined.AccessTime,
                title = "Usia",
                value = "Minimal 22 Tahun",
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            RequirementItem(
                icon = Icons.Outlined.Work,
                title = "Pengalaman",
                value = if (job.title.contains("Engineer") || job.title.contains("Developer") || job.title.contains("Analyst") || job.title.contains("Scientist") || job.title.contains("Architect"))
                    "Minimal 2 tahun" else "Minimal 1 tahun",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun RequirementItem(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = OrangePrimary,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF6B7280),
                fontWeight = FontWeight.Medium
            )
        }

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A1A),
            lineHeight = 18.sp
        )
    }
}

@Composable
fun SkillChip(
    skill: String,
    iconColor: Color = OrangePrimary,
    textColor: Color = Gray
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = LightGray,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.checkicon),
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = skill,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

//generate content based on job details
private fun generateJobDescriptionPoints(job: Job): List<String> {
    val basePoints = mutableListOf<String>()

    if (job.title.contains("WFH", ignoreCase = true) && !job.title.contains("Freelance", ignoreCase = true)) {
        basePoints.add("Bekerja secara remote dari rumah dengan jadwal kerja yang fleksibel")
        basePoints.add("Menggunakan tools kolaborasi seperti Slack, Zoom, dan project management software")
        basePoints.add("Melakukan daily standup dan meeting rutin dengan tim secara virtual")
        basePoints.add("Memastikan deliverable sesuai dengan timeline yang telah ditentukan")
        basePoints.add("Berkomunikasi aktif dengan tim dan stakeholder melalui platform digital")
    } else if (job.title.contains("Freelance") || job.salary.contains("Misi")) {
        basePoints.add("Misi membutuhkan KTP")
        basePoints.add("Reward sebesar ${job.salary.replace("Rp", "Rp")} untuk 1 misi yang dikerjakan")
    } else {
        basePoints.add("Mengembangkan dan memelihara ${getJobSpecificTask(job)}")
        basePoints.add("Berkolaborasi dengan tim untuk ${getJobSpecificCollaboration(job)}")
        basePoints.add("Memastikan kualitas dan performa ${getJobSpecificQuality(job)}")
        basePoints.add("Mengikuti perkembangan teknologi terbaru di bidang ${getJobSpecificField(job)}")
    }

    return basePoints
}

private fun generateBenefits(job: Job): List<String> {
    val benefits = mutableListOf<String>()

    if (job.title.contains("WFH", ignoreCase = true) && !job.title.contains("Freelance", ignoreCase = true)) {
        benefits.add("BPJS Kesehatan & Ketenagakerjaan")
        benefits.add("Fleksibilitas waktu kerja")
        benefits.add("Bekerja dari rumah secara penuh")
        benefits.add("Tunjangan internet dan listrik")
        benefits.add("Laptop dan peralatan kerja disediakan")
        benefits.add("Cuti berbayar")
        benefits.add("Pelatihan dan pengembangan skill online")
        if (job.title.contains("Developer") || job.title.contains("Engineer")) {
            benefits.add("Bonus tahunan")
            benefits.add("Tunjangan jabatan")
        }
    } else if (job.title.contains("Freelance") || job.salary.contains("Misi")) {
        benefits.add("Fleksibilitas waktu kerja")
        benefits.add("Bekerja dari mana saja")
        benefits.add("Pembayaran cepat setelah misi selesai")
    } else {
        benefits.add("BPJS Kesehatan & Ketenagakerjaan")
        benefits.add("Tunjangan hari raya")
        benefits.add("Cuti berbayar")
        if (job.title.contains("Developer") || job.title.contains("Engineer")) {
            benefits.add("Bonus tahunan")
            benefits.add("Tunjangan jabatan")
        }
        if (!job.type.contains("Part")) {
            benefits.add("Makan siang")
        }
        benefits.add("Lingkungan kerja yang nyaman")
    }

    return benefits
}

private fun getJobSpecificTask(job: Job): String {
    return when {
        job.title.contains("Frontend") -> "antarmuka pengguna yang responsif dan menarik"
        job.title.contains("Backend") -> "API dan sistem backend yang handal"
        job.title.contains("Mobile") -> "aplikasi mobile yang user-friendly"
        job.title.contains("Data") -> "model analisis data dan visualisasi"
        job.title.contains("DevOps") -> "infrastruktur dan pipeline deployment"
        job.title.contains("Cloud") -> "arsitektur cloud yang scalable"
        job.title.contains("Security") || job.title.contains("Cyber") -> "sistem keamanan dan protokol"
        job.title.contains("Network") -> "infrastruktur jaringan"
        job.title.contains("Scientist") -> "model prediktif dan algoritma machine learning"
        else -> "sistem dan aplikasi"
    }
}

private fun getJobSpecificCollaboration(job: Job): String {
    return when {
        job.title.contains("Frontend") -> "mengintegrasikan UI dengan backend API"
        job.title.contains("Backend") -> "memastikan integrasi yang mulus dengan frontend"
        job.title.contains("Mobile") -> "mengembangkan fitur aplikasi yang sesuai kebutuhan pengguna"
        job.title.contains("Data") -> "menganalisis dan menginterpretasikan data bisnis"
        job.title.contains("DevOps") -> "mengotomatisasi proses deployment dan testing"
        job.title.contains("Cloud") -> "mengoptimalkan penggunaan layanan cloud"
        job.title.contains("Security") || job.title.contains("Cyber") -> "mengidentifikasi dan mengatasi kerentanan keamanan"
        job.title.contains("Network") -> "memastikan konektivitas dan performa jaringan"
        job.title.contains("Scientist") -> "mengembangkan solusi berbasis data untuk masalah bisnis"
        else -> "mencapai tujuan proyek"
    }
}

private fun getJobSpecificQuality(job: Job): String {
    return when {
        job.title.contains("Frontend") -> "antarmuka pengguna"
        job.title.contains("Backend") -> "API dan layanan"
        job.title.contains("Mobile") -> "aplikasi mobile"
        job.title.contains("Data") -> "model analisis dan laporan"
        job.title.contains("DevOps") -> "pipeline dan infrastruktur"
        job.title.contains("Cloud") -> "layanan cloud"
        job.title.contains("Security") || job.title.contains("Cyber") -> "sistem keamanan"
        job.title.contains("Network") -> "jaringan"
        job.title.contains("Scientist") -> "model prediktif dan algoritma"
        else -> "produk"
    }
}

private fun getJobSpecificField(job: Job): String {
    return when {
        job.title.contains("Frontend") -> "pengembangan frontend"
        job.title.contains("Backend") -> "pengembangan backend"
        job.title.contains("Mobile") -> "pengembangan aplikasi mobile"
        job.title.contains("Data") -> "data science"
        job.title.contains("DevOps") -> "DevOps dan CI/CD"
        job.title.contains("Cloud") -> "teknologi cloud"
        job.title.contains("Security") || job.title.contains("Cyber") -> "keamanan siber"
        job.title.contains("Network") -> "jaringan"
        job.title.contains("Scientist") -> "data science dan machine learning"
        else -> "teknologi informasi"
    }
}

suspend fun getJobById(jobId: String?, context: Context): Job? {
    if (jobId == null) return null

    return try {
        val decodedJobId = URLDecoder.decode(jobId, "UTF-8")
        val jobRepository = (context.applicationContext as MyApplication).appContainer.jobRepository

        val allJobs = jobRepository.getAllJobs().first()

        allJobs.find { it.title == decodedJobId }
            ?: allJobs.find { it.title.equals(decodedJobId, ignoreCase = true) }
            ?: allJobs.find {
                it.title.contains(decodedJobId, ignoreCase = true) ||
                        decodedJobId.contains(it.title, ignoreCase = true)
            }
    } catch (e: Exception) {
        Log.e("JobDetailScreen", "Error finding job: ${e.message}")
        null
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFBF9, widthDp = 400, heightDp = 900)
@Composable
fun JobDetailScreenPreview() {
    MaterialTheme {
        JobDetailScreen(
            navController = rememberNavController(),
            jobId = "Frontend Developer"
        )
    }
}
