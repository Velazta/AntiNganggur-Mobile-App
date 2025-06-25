package com.l0123118.ravelin.antinganggur.menulist.profilepage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.MyApplication
import com.l0123118.ravelin.antinganggur.data.api.model.Experience
import com.l0123118.ravelin.antinganggur.di.ViewModelFactory
import com.l0123118.ravelin.antinganggur.ui.profile.ExperienceUiState
import com.l0123118.ravelin.antinganggur.ui.profile.ProfileViewModel
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as MyApplication).appContainer.profileRepository)
    )
) {
    val uiState by viewModel.experienceState.collectAsState()

    // Panggil fetch data saat pertama kali layar dibuka
    LaunchedEffect(Unit) {
        viewModel.fetchUserExperiences()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pengalaman Kerja", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is ExperienceUiState.Loading -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is ExperienceUiState.Success -> {
                ExperienceContent(
                    modifier = Modifier.padding(paddingValues),
                    experiences = state.experiences,
                    onAddExperience = { newExperience ->
                        viewModel.addExperience(newExperience)
                    },
                    onDeleteExperience = { experienceId ->
                        viewModel.deleteExperience(experienceId)
                    }
                )
            }
            is ExperienceUiState.Error -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues), contentAlignment = Alignment.Center) {
                    Text(text = state.message)
                }
            }
        }
    }
}

@Composable
fun ExperienceContent(
    modifier: Modifier = Modifier,
    experiences: List<Experience>,
    onAddExperience: (Experience) -> Unit,
    onDeleteExperience: (Int) -> Unit
) {
    var showAddForm by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (experiences.isNotEmpty()) {
            items(experiences) { experience ->
                ExperienceItem(
                    experience = experience,
                    onEditClick = { /* TODO: Navigasi ke halaman edit */ },
                    onDeleteClick = { onDeleteExperience(experience.id) }
                )
            }
        } else if (!showAddForm) {
            item {
                Text(
                    text = "Anda belum menambahkan pengalaman kerja.",
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = Color.Gray
                )
            }
        }

        item {
            AnimatedVisibility(!showAddForm) {
                OutlinedButton(
                    onClick = { showAddForm = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Tambah Pengalaman Baru")
                }
            }
        }

        item {
            AnimatedVisibility(showAddForm) {
                AddExperienceForm(
                    onSaveClick = { newExperience ->
                        onAddExperience(newExperience)
                        showAddForm = false
                    },
                    onCancelClick = { showAddForm = false }
                )
            }
        }
    }
}

@Composable
fun ExperienceItem(
    experience: Experience,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = experience.jobTitle,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    IconButton(onClick = onEditClick, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.Edit, "Edit", tint = Color.Gray)
                    }
                    IconButton(onClick = onDeleteClick, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.Delete, "Hapus", tint = Color.Red)
                    }
                }
            }
            Text(
                text = experience.companyName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            val endDate = if (experience.isCurrentJob) "Sekarang" else "${experience.endMonth}/${experience.endYear}"
            Text(
                text = "${experience.startMonth}/${experience.startYear} - $endDate",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                text = "${experience.city}, ${experience.country}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            if (!experience.description.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = experience.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExperienceForm(
    onSaveClick: (Experience) -> Unit,
    onCancelClick: () -> Unit
) {
    val years = (Calendar.getInstance().get(Calendar.YEAR) downTo 1970).map { it.toString() }
    val months = listOf("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
    val monthMap = months.mapIndexed { index, name -> name to (index + 1) }.toMap()
    val monthNameMap = (1..12).associateWith { months[it - 1] }

    // State untuk setiap field form
    var jobTitle by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("Indonesia") }
    var city by remember { mutableStateOf("") }
    var startMonth by remember { mutableStateOf(months[0]) }
    var startYear by remember { mutableStateOf(years[0]) }
    var endMonth by remember { mutableStateOf(months[0]) }
    var endYear by remember { mutableStateOf(years[0]) }
    var isCurrentJob by remember { mutableStateOf(false) }
    var description by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(
            "Tambah Pengalaman Baru",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Divider(modifier = Modifier.padding(top = 8.dp))

        // Menggunakan LabeledTextField yang sudah kita buat
        LabeledTextField(label = "Posisi Kerja", value = jobTitle, onValueChange = { jobTitle = it }, placeholder = "Contoh: Software Engineer")
        LabeledTextField(label = "Nama Perusahaan", value = companyName, onValueChange = { companyName = it }, placeholder = "Contoh: PT Teknologi Maju")
        LabeledTextField(label = "Negara", value = country, onValueChange = { country = it }, placeholder = "Contoh: Indonesia")
        LabeledTextField(label = "Kota", value = city, onValueChange = { city = it }, placeholder = "Contoh: Jakarta")

        Text("Tanggal Mulai", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DropdownMenuInput(label = "Bulan", selectedOption = startMonth, options = months, onOptionSelected = { startMonth = it }, modifier = Modifier.weight(1f))
            DropdownMenuInput(label = "Tahun", selectedOption = startYear, options = years, onOptionSelected = { startYear = it }, modifier = Modifier.weight(1f))
        }

        AnimatedVisibility(!isCurrentJob) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Tanggal Berakhir", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    DropdownMenuInput(label = "Bulan", selectedOption = endMonth, options = months, onOptionSelected = { endMonth = it }, modifier = Modifier.weight(1f))
                    DropdownMenuInput(label = "Tahun", selectedOption = endYear, options = years, onOptionSelected = { endYear = it }, modifier = Modifier.weight(1f))
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
            Checkbox(checked = isCurrentJob, onCheckedChange = { isCurrentJob = it })
            Text("Saya masih bekerja di sini", modifier = Modifier.clickable { isCurrentJob = !isCurrentJob })
        }

        LabeledTextField(label = "Deskripsi Pekerjaan", value = description, onValueChange = { description = it }, singleLine = false, minLines = 5, placeholder = "Jelaskan tanggung jawab dan pencapaian Anda")

        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.End) {
            OutlinedButton(onClick = onCancelClick) { Text("Batal") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                val newExperience = Experience(
                    id = 0, userId = 0,
                    jobTitle = jobTitle,
                    companyName = companyName,
                    country = country.ifEmpty { null },
                    city = city.ifEmpty { null },
                    startMonth = monthMap[startMonth],
                    startYear = startYear.toIntOrNull(),
                    endMonth = if (isCurrentJob) null else monthMap[endMonth],
                    endYear = if (isCurrentJob) null else endYear.toIntOrNull(),
                    isCurrentJob = isCurrentJob,
                    description = description.ifEmpty { null },
                    createdAt = null, updatedAt = null
                )
                onSaveClick(newExperience)
            }) { Text("Simpan") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuInput(label: String, selectedOption: String, options: List<String>, onOptionSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }, modifier = modifier) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(option) }, onClick = {
                    onOptionSelected(option)
                    expanded = false
                })
            }
        }
    }
}

@Composable
private fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    minLines: Int = 1,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholder: String = ""
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = Color.LightGray
            ),
            readOnly = readOnly,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = singleLine,
            minLines = minLines,
            trailingIcon = trailingIcon,
            placeholder = { Text(text = placeholder)}
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewExperienceScreen()
{
    ANTINGANGGURTheme {
        ExperienceScreen(navController = rememberNavController())
    }
}