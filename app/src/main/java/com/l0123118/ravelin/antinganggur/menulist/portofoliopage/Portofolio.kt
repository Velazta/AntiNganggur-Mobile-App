package com.l0123118.ravelin.antinganggur.menulist.portofoliopage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
import com.l0123118.ravelin.antinganggur.ui.theme.LightOrange
import com.l0123118.ravelin.antinganggur.ui.theme.Gray
import com.l0123118.ravelin.antinganggur.ui.theme.LightGray
import com.l0123118.ravelin.antinganggur.ui.theme.PrimaryOrange
import kotlinx.coroutines.delay

class Portofolio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANTINGANGGURTheme {
                PortofolioPage(navController = rememberNavController())
            }
        }
    }
}

data class Project(
    val title: String,
    val description: String,
    val imageResId: Int,
    val technologies: List<String>
)

data class ClientReview(
    val name: String,
    val company: String,
    val review: String,
    val rating: Int,
)

@Composable
fun PortofolioPage(navController: NavController) {
    val projects = remember {
        listOf(
            Project(
                title = "AntiNganggur Mobile App",
                description = "Aplikasi mobile untuk pencarian kerja di bidang IT dengan fitur lengkap dan user-friendly interface",
                imageResId = R.drawable.porto1,
                technologies = listOf("Kotlin", "Jetpack Compose", "Firebase")
            ),
            Project(
                title = "Job Portal Website",
                description = "Platform web untuk menghubungkan perusahaan dengan kandidat terbaik di bidang teknologi",
                imageResId = R.drawable.porto2,
                technologies = listOf("React", "Node.js", "MongoDB")
            ),
            Project(
                title = "HR Management System",
                description = "Sistem manajemen HR terintegrasi untuk mengelola karyawan dan proses rekrutmen",
                imageResId = R.drawable.porto3,
                technologies = listOf("Vue.js", "Laravel", "MySQL")
            )
        )
    }

    val clientReviews = remember {
        listOf(
            ClientReview("Budi Santoso", "Mobile App Developer", "Pelayanan sangat memuaskan dan hasil kerja berkualitas tinggi. Tim AntiNganggur sangat profesional.", 5),
            ClientReview("Sari Dewi", "Web Developer", "Proyek diselesaikan tepat waktu dengan kualitas yang melebihi ekspektasi kami.", 5),
            ClientReview("Ahmad Rahman", "Backend Engineer", "Komunikasi yang baik dan pemahaman kebutuhan bisnis yang mendalam.", 4),
            ClientReview("Linda Kusuma", "UI/UX Designer", "Sangat puas dengan hasil akhir dan dukungan teknis yang diberikan.", 5),
            ClientReview("Rudi Hartono", "DevOps Engineer", "Tim yang responsif dan solusi yang inovatif untuk kebutuhan perusahaan kami.", 5),
            ClientReview("Maya Sari", "Frontend Developer", "Kerjasama yang sangat baik dan hasil yang melampaui harapan.", 4),
            ClientReview("Eko Prasetyo", "Full Stack Developer", "Profesional, tepat waktu, dan hasil kerja yang berkualitas tinggi.", 5),
            ClientReview("Rina Wati", "QA Engineer", "Sangat merekomendasikan AntiNganggur untuk proyek teknologi.", 5)
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightOrange)
    ) {
        item {
            HeroSection()
        }
        item {
            ProjectSection(projects = projects)
        }

        item {
            ClientReviewSection(reviews = clientReviews)
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(750.dp)
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.senyum),
            contentDescription = "Hero Background",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-80).dp)
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(280.dp))
            Text(
                text = "Komitmen",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary,
                lineHeight = 40.sp
            )
            Text(
                text = "Pada Kualitas",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary,
                lineHeight = 40.sp
            )
            Text(
                text = "Bukti Nyata",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary.copy(alpha = 0.8f),
                lineHeight = 40.sp
            )
            Text(
                text = "Melalui Karya",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary.copy(alpha = 0.8f),
                lineHeight = 40.sp
            )
            Text(
                text = "Jelajahi",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary.copy(alpha = 0.75f)
            )
            Text(
                text = "Portofolio Kami",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary.copy(alpha = 0.75f)
            )
        }
    }
}

@Composable
fun ProjectSection(projects: List<Project>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightOrange)
            .padding(24.dp)
    ) {
        Text(
            text = "Project Kami",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = OrangePrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Tujuan AntiNganggur adalah memberdayakan banyak pemuda untuk mengikuti pelatihan keterampilan yang\n" +
                    "relevan dengan industri, sehingga dapat membantu mereka mendapatkan pekerjaan yang lebih baik.",
            fontSize = 16.sp,
            color = Gray,
            lineHeight = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )

        // Project Carousel
        ProjectCarousel(projects = projects)
    }
}

@Composable
fun ProjectCarousel(projects: List<Project>) {
    val pagerState = rememberPagerState(pageCount = { projects.size })

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            ProjectCard(project = projects[page])
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Page Indicators
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(projects.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .size(if (isSelected) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) OrangePrimary else Gray.copy(alpha = 0.5f)
                        )
                        .padding(horizontal = 4.dp)
                )
                if (index < projects.size - 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

@Composable
fun ProjectCard(project: Project) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = project.imageResId),
                contentDescription = project.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = project.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = project.description,
                    fontSize = 14.sp,
                    color = Gray,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(project.technologies) { tech ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = LightGray
                        ) {
                            Text(
                                text = tech,
                                fontSize = 12.sp,
                                color = Gray,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClientReviewSection(reviews: List<ClientReview>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightOrange)
            .padding(vertical = 32.dp)
    ) {
        Text(
            text = "Ulasan Klien",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = OrangePrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Text(
            text = "Kami menyediakan solusi yang tepat waktu, efektif, dan membangun hubungan yang kuat dengan klien",
            fontSize = 16.sp,
            color = Gray,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        )

        AnimatedReviewRows(reviews = reviews)
    }
}

@Composable
fun AnimatedReviewRows(reviews: List<ClientReview>) {
    val chunkedReviews = reviews.chunked(2)

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        chunkedReviews.forEachIndexed { rowIndex, rowReviews ->
            AnimatedReviewRow(
                reviews = rowReviews,
                scrollLeft = rowIndex % 2 == 0
            )
        }
    }
}

@Composable
fun AnimatedReviewRow(reviews: List<ClientReview>, scrollLeft: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "review_scroll")
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = if (scrollLeft) 0f else -200f,
        targetValue = if (scrollLeft) -200f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "offset_animation"
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        // Duplicate items for infinite scroll effect
        items(reviews + reviews) { review ->
            ReviewCard(
                review = review,
                modifier = Modifier.offset(x = animatedOffset.dp)
            )
        }
    }
}

@Composable
fun ReviewCard(review: ClientReview, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(280.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                // Avatar inisial (selalu pakai huruf)
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(OrangePrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = review.name.firstOrNull()?.uppercaseChar()?.toString() ?: "?",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = review.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = review.company,
                        fontSize = 14.sp,
                        color = Gray
                    )
                }
            }

            Row(
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                repeat(5) { index ->
                    Text(
                        text = if (index < review.rating) "★" else "☆",
                        color = if (index < review.rating) OrangePrimary else Gray,
                        fontSize = 16.sp
                    )
                }
            }

            Text(
                text = review.review,
                fontSize = 14.sp,
                color = Color.Black,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PortofolioPagePreview() {
    ANTINGANGGURTheme {
        PortofolioPage(navController = rememberNavController())
    }
}
