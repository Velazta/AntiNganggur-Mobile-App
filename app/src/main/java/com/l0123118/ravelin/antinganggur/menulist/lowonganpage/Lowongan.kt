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
import com.l0123118.ravelin.antinganggur.data.repository.JobRepository

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

@Composable
fun LowonganScreen(
    navController: NavController = rememberNavController(),
    viewModel: JobViewModel = viewModel()
) {
    val trendingJobs by viewModel.trendingJobs.collectAsState()
    val recentJobs by viewModel.recentJobs.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var keyword by remember { mutableStateOf("") }
    var locationFilter by remember { mutableStateOf("Semua Lokasi") }

    val filteredRecentJobs = if (searchResults.isNotEmpty()) searchResults else recentJobs

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

        items(filteredRecentJobs) { job ->
            JobDetailCard(
                job,
                navController = navController,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
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
