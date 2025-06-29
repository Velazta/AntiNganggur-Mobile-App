package com.l0123118.ravelin.antinganggur.menulist.profilepage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.MyApplication
import com.l0123118.ravelin.antinganggur.data.api.model.Education
import com.l0123118.ravelin.antinganggur.di.ViewModelFactory
import com.l0123118.ravelin.antinganggur.ui.profile.EducationUiState
import com.l0123118.ravelin.antinganggur.ui.profile.ProfileViewModel
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as MyApplication).appContainer.profileRepository)
    )
) {
    val uiState by viewModel.educationState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserEducations()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Riwayat Pendidikan", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is EducationUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is EducationUiState.Success -> {
                EducationContent(
                    modifier = Modifier.padding(paddingValues),
                    educations = state.educations,
                    onUpdateEducation = { updatedEducation -> viewModel.updateEducation(updatedEducation) },
                    onAddEducation = { newEducation -> viewModel.addEducation(newEducation) },
                    onDeleteEducation = { educationId -> viewModel.deleteEducation(educationId) }
                )
            }
            is EducationUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    Text(text = state.message)
                }
            }
        }
    }
}

@Composable
fun EducationContent(
    modifier: Modifier = Modifier,
    educations: List<Education>,
    onAddEducation: (Education) -> Unit,
    onUpdateEducation: (Education) -> Unit,
    onDeleteEducation: (Int) -> Unit
) {
    var educationToEdit by remember { mutableStateOf<Education?>(null) }
    var showAddForm by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (educations.isNotEmpty()) {
            items(educations) { education ->
                EducationItem(
                    education = education,
                    onEditClick = { educationToEdit = education },
                    onDeleteClick = { onDeleteEducation(education.id) }
                )
            }
        } else if (!showAddForm && educationToEdit == null) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Anda belum menambahkan riwayat pendidikan.",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Gray
                    )
                }
            }
        }

        item {
            AnimatedVisibility(visible = !showAddForm && educationToEdit == null) {
                OutlinedButton(
                    onClick = { showAddForm = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, OrangePrimary)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah", tint = OrangePrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Tambah Riwayat Pendidikan", color = OrangePrimary)
                }
            }
        }

        item {
            AnimatedVisibility(showAddForm) {
                EducationForm(
                    onSaveClick = { newEducation ->
                        onAddEducation(newEducation)
                        showAddForm = false
                    },
                    onCancelClick = { showAddForm = false }
                )
            }
        }

        item {
            educationToEdit?.let { education ->
                EducationForm(
                    initialData = education, // Kirim data awal ke form
                    onSaveClick = { updatedEducation ->
                        onUpdateEducation(updatedEducation)
                        educationToEdit = null // Tutup form setelah simpan
                    },
                    onCancelClick = { educationToEdit = null } // Tutup form saat batal
                )
            }
        }
    }
}

@Composable
fun EducationItem(
    education: Education,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(education.universityName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Row {
                    IconButton(onClick = onEditClick, modifier = Modifier.size(24.dp)) { Icon(Icons.Default.Edit, "Edit", tint = Color.Gray) }
                    IconButton(onClick = onDeleteClick, modifier = Modifier.size(24.dp)) { Icon(Icons.Default.Delete, "Hapus", tint = Color.Red) }
                }
            }
            Text("${education.degree}, ${education.major}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            val endDate = if (education.currentlyStudying) "Sekarang" else "${education.endMonth} ${education.endYear}"
            Text("Lulus: $endDate", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Text("IPK: ${education.ipk}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}

@Composable
fun EducationForm(
    initialData: Education? = null,
    onSaveClick: (Education) -> Unit,
    onCancelClick: () -> Unit
) {
    val years = (Calendar.getInstance().get(Calendar.YEAR) downTo 1970).map { it.toString() }
    val months = listOf("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
    val monthMap = months.mapIndexed { index, name -> name to months[index] }.toMap()
    val monthNameMap = months.withIndex().associate { (index, name) -> (index + 1).toString() to name }

    var universityName by remember { mutableStateOf(initialData?.universityName ?: "") }
    var degree by remember { mutableStateOf(initialData?.degree ?: "") }
    var major by remember { mutableStateOf(initialData?.major ?: "") }
    var country by remember { mutableStateOf(initialData?.country?: "") }
    var city by remember { mutableStateOf(initialData?.city?: "") }
    var startMonth by remember { mutableStateOf(initialData?.startMonth ?: months[0]) }
    var startYear by remember { mutableStateOf(initialData?.startYear ?: years[0]) }
    var endMonth by remember { mutableStateOf(initialData?.endMonth ?: months[0]) }
    var endYear by remember { mutableStateOf(initialData?.endYear ?: years[0]) }
    var isCurrentlyStudying by remember { mutableStateOf(initialData?.currentlyStudying ?: false) }
    var ipk by remember { mutableStateOf(initialData?.ipk?.toString() ?: "") }
    var description by remember { mutableStateOf(initialData?.description ?: "") }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 16.dp)) {
        Text(
            if (initialData == null) "Tambah Riwayat Pendidikan" else "Edit Riwayat Pendidikan",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Divider(modifier = Modifier.padding(top = 8.dp))

        LabeledTextField(label = "Nama Institusi", value = universityName, onValueChange = { universityName = it }, placeholder = "Contoh: Universitas Sebelas Maret")
        LabeledTextField(label = "Gelar", value = degree, onValueChange = { degree = it }, placeholder = "Contoh: Sarjana Komputer")
        LabeledTextField(label = "Jurusan", value = major, onValueChange = { major = it }, placeholder = "Contoh: Ilmu Komputer")
        LabeledTextField(label = "Negara", value = country, onValueChange = { country = it }, placeholder = "Contoh: Indonesia")
        LabeledTextField(label = "Kota", value = city, onValueChange = { city = it }, placeholder = "Contoh: Yogyakarta")

        Text("Tanggal Mulai", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DropdownMenuInput("Bulan", startMonth, months, { startMonth = it }, Modifier.weight(1f))
            DropdownMenuInput("Tahun", startYear, years, { startYear = it }, Modifier.weight(1f))
        }

        AnimatedVisibility(!isCurrentlyStudying) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Tanggal Berakhir", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    DropdownMenuInput("Bulan", endMonth, months, { endMonth = it }, Modifier.weight(1f))
                    DropdownMenuInput("Tahun", endYear, years, { endYear = it }, Modifier.weight(1f))
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
            Checkbox(checked = isCurrentlyStudying,
                onCheckedChange = { isCurrentlyStudying = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = OrangePrimary,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Text("Saya masih menempuh pendidikan di sini", modifier = Modifier.clickable { isCurrentlyStudying = !isCurrentlyStudying })
        }

        LabeledTextField(label = "IPK", value = ipk, onValueChange = { ipk = it }, placeholder = "Contoh: 3.75", keyboardType = KeyboardType.Number)
        LabeledTextField(label = "Deskripsi Tambahan", value = description, onValueChange = { description = it }, singleLine = false, minLines = 4, placeholder = "Jelaskan kegiatan relevan, prestasi, atau informasi lain (opsional)")

        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.End) {
            OutlinedButton(onClick = onCancelClick) { Text("Batal") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                val newEducation = Education(
                    id = initialData?.id ?: 0,
                    userId = initialData?.userId ?: 0,
                    universityName = universityName,
                    degree = degree,
                    major = major,
                    country = country,
                    city = city,
                    startMonth = startMonth,
                    startYear = startYear,
                    endMonth = if (isCurrentlyStudying) null else endMonth,
                    endYear = if (isCurrentlyStudying) null else endYear,
                    currentlyStudying = isCurrentlyStudying,
                    ipk = ipk.toDoubleOrNull(),
                    description = description.ifEmpty { null },
                    createdAt = initialData?.createdAt,
                    updatedAt = null
                )
                onSaveClick(newEducation)
            },shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary))
            { Text("Simpan") }
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
fun previewEducationScreen()
{
    ANTINGANGGURTheme {
        EducationScreen(navController = rememberNavController())
    }
}

