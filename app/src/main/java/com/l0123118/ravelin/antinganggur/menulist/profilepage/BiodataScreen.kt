package com.l0123118.ravelin.antinganggur.menulist.profilepage

import android.app.Dialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.l0123118.ravelin.antinganggur.data.api.model.Profile
import com.l0123118.ravelin.antinganggur.di.AppContainer
import com.l0123118.ravelin.antinganggur.di.ViewModelFactory
import com.l0123118.ravelin.antinganggur.MyApplication
import com.l0123118.ravelin.antinganggur.ui.profile.ProfileUiState
import com.l0123118.ravelin.antinganggur.ui.profile.ProfileViewModel
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as MyApplication).appContainer.profileRepository)
    )
) {
    val uiState by viewModel.profileState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchUserProfile()
    }

    if (viewModel.showSuccessDialog) {
        CustomSuccessDialog(
            onDismissRequest = { viewModel.dialogMessageShown() },
            isSuccess = viewModel.isUpdateSuccess,
            message = viewModel.dialogMessage
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Biodata Diri", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White, titleContentColor = Color.Black)
            )
        },

        containerColor = Color.White
    ) { paddingValues ->
        when (val state = uiState) {
            is ProfileUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is ProfileUiState.Success -> {
                BiodataForm(
                    modifier = Modifier.padding(paddingValues),
                    profile = state.profile,
                    // PERBAIKAN: Berikan lambda yang memanggil fungsi di ViewModel
                    onSaveClick = { updatedProfile ->
                        viewModel.updateProfile(updatedProfile)
                    }
                )
            }
            is ProfileUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = state.message)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataForm(
    modifier: Modifier = Modifier,
    profile: Profile,
    onSaveClick: (Profile) -> Unit
) {
    // State untuk setiap field agar bisa diedit
    var name by remember { mutableStateOf(profile.name ?: "") }
    var phoneNumber by remember { mutableStateOf(profile.phoneNumber ?: "") }
    var country by remember { mutableStateOf(profile.country ?: "") }
    var province by remember { mutableStateOf(profile.province ?: "") }
    var city by remember { mutableStateOf(profile.city ?: "") }
    var dateOfBirth by remember { mutableStateOf(profile.dateOfBirth ?: "") }
    var selectedGender by remember { mutableStateOf(profile.gender) }
    var address by remember { mutableStateOf(profile.address ?: "") }
    var bio by remember { mutableStateOf(profile.bio ?: "") }

    // --- State untuk Date Picker ---
    val context = LocalContext.current
    val showDatePicker = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    if (showDatePicker.value) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker.value = false
                        // Ambil tanggal yang dipilih (dalam milidetik) dan format
                        datePickerState.selectedDateMillis?.let { millis ->
                            // Format ke yyyy-MM-dd agar sesuai dengan standar database
                            val formatter = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                            dateOfBirth = formatter.format(java.util.Date(millis))
                        }
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker.value = false }) { Text("Batal") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(profile.profilePhotoPath ?: "https://via.placeholder.com/150").crossfade(true).build(),
                contentDescription = "Foto Profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ubah Foto",
                color = OrangePrimary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { /* TODO: Buka galeri/kamera */ }
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        item { LabeledTextField(label = "Nama Lengkap", value = name, onValueChange = { name = it }) }
        item { LabeledTextField(label = "Email", value = profile.email ?: "", onValueChange = {}, readOnly = true) }
        item { LabeledTextField(label = "Nomor HP", value = phoneNumber, onValueChange = { phoneNumber = it }, keyboardType = KeyboardType.Phone) }
        item { LabeledTextField(label = "Negara", value = country, onValueChange = { country = it }) }
        item { LabeledTextField(label = "Provinsi", value = province, onValueChange = { province = it }) }
        item { LabeledTextField(label = "Kota", value = city, onValueChange = { city = it }) }
        item {
            Box(modifier = Modifier.clickable { showDatePicker.value = true }) { // Buat seluruh area bisa diklik
                LabeledTextField(
                    label = "Tanggal Lahir",
                    value = dateOfBirth,
                    onValueChange = { /* Dikosongkan karena input dari dialog */ },
                    readOnly = true,
                    trailingIcon = { Icon(Icons.Default.CalendarToday, "Kalender") }
                )
            }
        }
        item { GenderSelector(selectedGender = selectedGender, onGenderSelected = { selectedGender = it }) }
        item { LabeledTextField(label = "Alamat", value = address, onValueChange = { address = it }, singleLine = false, minLines = 3) }
        item { LabeledTextField(label = "Bio", value = bio, onValueChange = { bio = it }, singleLine = false, minLines = 4, placeholder = "Cerita Singkat Diri Anda") }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    val updatedProfile = profile.copy(
                        name = name,
                        phoneNumber = phoneNumber,
                        country = country,
                        province = province,
                        city = city,
                        dateOfBirth = dateOfBirth,
                        gender = selectedGender,
                        address = address,
                        bio = bio
                    )
                    onSaveClick(updatedProfile)
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
            ) {
                Text("Simpan Perubahan", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(32.dp))
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

@Composable
private fun GenderSelector(
    selectedGender: String?,
    onGenderSelected: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Jenis Kelamin",
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onGenderSelected("male") }
                    .padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = selectedGender == "male",
                    onClick = { onGenderSelected("male") },
                    colors = RadioButtonDefaults.colors(selectedColor = OrangePrimary)
                )
                Text("Pria")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onGenderSelected("female") }
                    .padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = selectedGender == "female",
                    onClick = { onGenderSelected("female") },
                    colors = RadioButtonDefaults.colors(selectedColor = OrangePrimary)
                )
                Text("Wanita")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CustomSuccessDialog(
    onDismissRequest: () -> Unit,
    isSuccess: Boolean,
    message: String
) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000L)
        onDismissRequest
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (isSuccess) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Success Icon",
                        modifier = Modifier.size(80.dp),
                        tint = Color(0xFF4CAF50) // Warna hijau
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error Icon",
                        modifier = Modifier.size(80.dp),
                        tint = Color(0xFFD32F2F) // Warna merah
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = message,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BiodataScreenPreview() {
    ANTINGANGGURTheme {
        BiodataScreen(navController = rememberNavController())
    }
}