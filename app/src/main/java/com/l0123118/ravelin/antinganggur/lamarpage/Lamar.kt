//UNDERDEVELOPED STILL BUG


//package com.l0123118.ravelin.antinganggur.menulist.lamarpage
//
//import android.net.Uri
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Upload
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.l0123118.ravelin.antinganggur.ui.theme.*
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.delay
//
//@Composable
//fun LamarScreen(navController: NavController) {
//    // Form states
//    var namaLengkap by remember { mutableStateOf("") }
//    var nomorHP by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var lokasi by remember { mutableStateOf("") }
//    var pendidikanTerakhir by remember { mutableStateOf("") }
//    var jurusan by remember { mutableStateOf("") }
//    var tingkatPengalaman by remember { mutableStateOf("") }
//    var cvUri by remember { mutableStateOf<Uri?>(null) }
//    var portofolioUri by remember { mutableStateOf<Uri?>(null) }
//
//    // Snackbar state
//    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()
//
//    // Form validation
//    val isFormValid by derivedStateOf {
//        namaLengkap.isNotBlank() &&
//                nomorHP.isNotBlank() &&
//                email.isNotBlank() &&
//                lokasi.isNotBlank() &&
//                pendidikanTerakhir.isNotBlank() &&
//                jurusan.isNotBlank() &&
//                tingkatPengalaman.isNotBlank() &&
//                cvUri != null
//    }
//
//    // File pickers
//    val cvPickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        cvUri = uri
//    }
//
//    val portfolioPickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        portofolioUri = uri
//    }
//
//    // Handle submission
//    fun handleSubmit() {
//        scope.launch {
//            // Show sending message
//            snackbarHostState.showSnackbar(
//                message = "Lamaran sedang dikirim...",
//                duration = SnackbarDuration.Short
//            )
//
//            // Short delay (0.5 second)
//            delay(500)
//
//            // Show success message
//            snackbarHostState.showSnackbar(
//                message = "Lamaran berhasil dikirim!",
//                duration = SnackbarDuration.Short
//            )
//
//            // Short delay before navigating back
//            delay(500)
//            navController.popBackStack()
//        }
//    }
//
//    Scaffold(
//        snackbarHost = { SnackbarHost(snackbarHostState) }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .background(Color.White)
//                .padding(padding)
//        ) {
//            // Header
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(PrimaryOrange)
//                    .padding(16.dp)
//            ) {
//                Column {
//                    Text(
//                        text = "Lamar Pekerjaan",
//                        style = MaterialTheme.typography.headlineLarge.copy(
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black
//                        )
//                    )
//                    Text(
//                        text = "Ke AntiNganggur",
//                        style = MaterialTheme.typography.titleMedium.copy(
//                            color = Color.Black
//                        )
//                    )
//                }
//            }
//
//            // Form content
//            Column(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Divider(
//                    color = LightGray,
//                    thickness = 1.dp,
//                    modifier = Modifier.padding(bottom = 16.dp)
//                )
//
//                // Form fields
//                FormTextField(
//                    label = "Nama Lengkap",
//                    placeholder = "Masukkan nama lengkap",
//                    value = namaLengkap,
//                    onValueChange = { namaLengkap = it }
//                )
//
//                FormTextField(
//                    label = "Nomor HP",
//                    placeholder = "Mulai Dengan Kode Negara",
//                    value = nomorHP,
//                    onValueChange = { nomorHP = it }
//                )
//
//                FormTextField(
//                    label = "Email",
//                    placeholder = "Masukkan Emailmu",
//                    value = email,
//                    onValueChange = { email = it }
//                )
//
//                FormTextField(
//                    label = "Lokasi",
//                    placeholder = "Input nama kabupaten/kota mu",
//                    value = lokasi,
//                    onValueChange = { lokasi = it }
//                )
//
//                FormTextField(
//                    label = "Pendidikan Terakhir",
//                    placeholder = "Cth: Universitas Sebelas Maret",
//                    value = pendidikanTerakhir,
//                    onValueChange = { pendidikanTerakhir = it }
//                )
//
//                FormTextField(
//                    label = "Jurusan",
//                    placeholder = "Cth: Informatika",
//                    value = jurusan,
//                    onValueChange = { jurusan = it }
//                )
//
//                FormTextField(
//                    label = "Tingkat Pengalaman",
//                    placeholder = "Cth: Fresh Graduate",
//                    value = tingkatPengalaman,
//                    onValueChange = { tingkatPengalaman = it }
//                )
//
//                // File uploads
//                FileUploadField(
//                    label = "CV (PDF)",
//                    placeholder = "Unggah CV dalam bentuk PDF",
//                    fileName = cvUri?.lastPathSegment ?: "",
//                    onUploadClick = { cvPickerLauncher.launch("application/pdf") }
//                )
//
//                FileUploadField(
//                    label = "Portofolio (PDF)",
//                    placeholder = "Unggah portofolio dalam bentuk PDF",
//                    fileName = portofolioUri?.lastPathSegment ?: "",
//                    onUploadClick = { portfolioPickerLauncher.launch("application/pdf") },
//                    isRequired = false
//                )
//
//                Spacer(modifier = Modifier.weight(1f))
//
//                // Submit button
//                Button(
//                    onClick = { handleSubmit() },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(56.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = if (isFormValid) PrimaryOrange else LightGray,
//                        contentColor = Color.White
//                    ),
//                    enabled = isFormValid
//                ) {
//                    Text(
//                        text = "Lamar",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun FormTextField(
//    label: String,
//    placeholder: String,
//    value: String,
//    onValueChange: (String) -> Unit
//) {
//    Column(modifier = Modifier.padding(bottom = 16.dp)) {
//        Text(
//            text = label,
//            style = MaterialTheme.typography.bodyLarge.copy(
//                fontWeight = FontWeight.Bold,
//                color = Color.Gray
//            ),
//            modifier = Modifier.padding(bottom = 4.dp)
//        )
//
//        OutlinedTextField(
//            value = value,
//            onValueChange = onValueChange,
//            placeholder = {
//                Text(
//                    text = placeholder,
//                    color = Color.Gray
//                )
//            },
//            modifier = Modifier.fillMaxWidth(),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = PrimaryOrange,
//                unfocusedBorderColor = LightGray,
//                focusedLabelColor = PrimaryOrange,
//                cursorColor = PrimaryOrange,
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black
//            ),
//            singleLine = true
//        )
//    }
//}
//
//@Composable
//fun FileUploadField(
//    label: String,
//    placeholder: String,
//    fileName: String,
//    onUploadClick: () -> Unit,
//    isRequired: Boolean = true
//) {
//    Column(modifier = Modifier.padding(bottom = 16.dp)) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Text(
//                text = label,
//                style = MaterialTheme.typography.bodyLarge.copy(
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Gray
//                ),
//                modifier = Modifier.padding(bottom = 4.dp)
//            )
//            if (isRequired) {
//                Text(
//                    text = "*",
//                    color = Color.Red,
//                    modifier = Modifier.padding(start = 2.dp, bottom = 4.dp)
//                )
//            }
//        }
//
//        if (fileName.isNotEmpty()) {
//            Text(
//                text = fileName,
//                color = PrimaryOrange,
//                modifier = Modifier.padding(bottom = 8.dp),
//                maxLines = 1
//            )
//        }
//
//        OutlinedButton(
//            onClick = onUploadClick,
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.outlinedButtonColors(
//                contentColor = PrimaryOrange
//            )
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Icon(
//                    imageVector = Icons.Default.Upload,
//                    contentDescription = "Upload",
//                    tint = if (fileName.isEmpty()) Color.Gray else PrimaryOrange
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = if (fileName.isEmpty()) placeholder else "Ganti File",
//                    color = if (fileName.isEmpty()) Color.Gray else PrimaryOrange
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LamarScreenPreview() {
//    ANTINGANGGURTheme {
//        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
//            LamarScreen(navController = rememberNavController())
//        }
//    }
//}