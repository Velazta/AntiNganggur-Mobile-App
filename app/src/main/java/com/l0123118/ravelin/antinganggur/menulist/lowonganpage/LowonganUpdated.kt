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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.BorderStroke
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.MyApplication
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.di.JobViewModelFactory
import com.l0123118.ravelin.antinganggur.navigation.Screen
import com.l0123118.ravelin.antinganggur.ui.theme.PrimaryOrange
import com.l0123118.ravelin.antinganggur.ui.theme.LightOrange
import com.l0123118.ravelin.antinganggur.ui.theme.BackgroundOrange
import com.l0123118.ravelin.antinganggur.ui.theme.LightPeach
import com.l0123118.ravelin.antinganggur.ui.theme.Gray
import com.l0123118.ravelin.antinganggur.ui.theme.LightGray
import android.util.Log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LowonganScreenUpdated(
    navController: NavController = rememberNavController(),
    viewModel: JobViewModel = viewModel(
        factory = JobViewModelFactory((LocalContext.current.applicationContext as MyApplication).appContainer.jobRepository)
    )
) {
    val trendingJobs by viewModel.trendingJobs.collectAsState()
    val recentJobs by viewModel.recentJobs.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var keyword by remember { mutableStateOf("") }
    var locationFilter by remember { mutableStateOf("Semua Lokasi") }

    fun doSearch() {
        viewModel.searchJobs(keyword, locationFilter)
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
                    SearchLowonganSection(
                        keyword, { keyword = it },
                        locationFilter, { locationFilter = it },
                        onSearch = { doSearch() }
                    )
                }
            }
        }

        // Show loading indicator
        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        } else {
            // Show search results if search is active
            if (searchResults.isNotEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(LightOrange)
                            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                    ) {
                        Text("Hasil Pencarian", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Ditemukan ${searchResults.size} lowongan", fontSize = 14.sp, color = Gray)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                items(searchResults) { job ->
                    JobDetailCard(
                        job,
                        navController = navController,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            } else {
                // Show trending jobs section
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

                items(recentJobs) { job ->
                    JobDetailCard(
                        job,
                        navController = navController,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

// JobCard dan JobDetailCard tetap sama seperti sebelumnya
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
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lamar", color = Color.White)
            }
        }
    }
}

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
                        // TODO: Implement apply job logic here (e.g., show a dialog, call ViewModel, etc.)
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
fun PreviewLowonganScreenUpdated() {
    MaterialTheme {
        Surface {
            LowonganScreenUpdated()
        }
    }
}
