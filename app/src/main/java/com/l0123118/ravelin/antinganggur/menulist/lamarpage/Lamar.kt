package com.l0123118.ravelin.antinganggur.menulist.lamarpage

import android.net.Uri
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.MyApplication
import com.l0123118.ravelin.antinganggur.data.local.database.ApplicationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lamar(
    navController: NavController,
    jobTitle: String?
) {
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }
    var experienceLevel by remember { mutableStateOf("") }
    var cvUri by remember { mutableStateOf<Uri?>(null) }
    var portfolioUri by remember { mutableStateOf<Uri?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // File picker launchers
    val cvLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        cvUri = uri
    }

    val portfolioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        portfolioUri = uri
    }

        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = Color(0xFFFF9B7A),
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.main),
                        contentDescription = "Job Application Illustration",
                        modifier = Modifier
                            .size(80.dp)
                            .weight(1f),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Title Text
                    Column(
                        modifier = Modifier.weight(1.5f)
                    ) {
                        Text(
                            text = "Lamar Pekerjaan",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 28.sp
                        )
                        Text(
                            text = "Ke AntiNganggur",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 28.sp
                        )

                        if (jobTitle != null) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Posisi: ${jobTitle.replace("+", " ")}",
                                fontSize = 14.sp,
                                color = Color.White.copy(alpha = 0.9f),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Form Section
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                // Full Name Field
                FormField(
                    label = "Nama Lengkap",
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = "Masukkan Nama Lengkap"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Phone Number Field
                FormField(
                    label = "Nomor",
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    placeholder = "Mulai Dengan Kode Negara",
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email Field
                FormField(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Masukkan Email Mu",
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Location Field
                FormField(
                    label = "Lokasi",
                    value = location,
                    onValueChange = { location = it },
                    placeholder = "Input Nama Kabupaten/Kota"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Education Field
                FormField(
                    label = "Pendidikan Terakhir",
                    value = education,
                    onValueChange = { education = it },
                    placeholder = "Cth: Universitas Sebelas Maret"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Major Field
                FormField(
                    label = "Jurusan",
                    value = major,
                    onValueChange = { major = it },
                    placeholder = "Cth: Informatika"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Experience Level Field
                FormField(
                    label = "Tingkat Pengalaman",
                    value = experienceLevel,
                    onValueChange = { experienceLevel = it },
                    placeholder = "Cth: Fresh Graduate"
                )

                Spacer(modifier = Modifier.height(24.dp))

                // CV Upload Section
                FileUploadSection(
                    label = "CV",
                    fileName = cvUri?.lastPathSegment ?: "Unggah CV Dalam Bentuk Pdf",
                    isUploaded = cvUri != null,
                    onClick = { cvLauncher.launch("application/pdf") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Portfolio Upload Section
                FileUploadSection(
                    label = "Portofolio",
                    fileName = portfolioUri?.lastPathSegment ?: "Unggah Portofolio Dalam Bentuk Pdf",
                    isUploaded = portfolioUri != null,
                    onClick = { portfolioLauncher.launch("application/pdf") }
                )

                Spacer(modifier = Modifier.height(32.dp))

                val applicationViewModel: ApplicationViewModel = viewModel(
                    factory = ApplicationViewModelFactory((LocalContext.current.applicationContext as MyApplication).appContainer.applicationRepository)
                )
                // Submit Button
                Button(
                    onClick = {
                        if (validateForm(fullName, phoneNumber, email, location, education, major, experienceLevel)&&
                            (cvUri != null)) {
                            val application = ApplicationEntity(
                            fullName = fullName,
                            phoneNumber = phoneNumber,
                            email = email,
                            location = location,
                            education = education,
                            major = major,
                            experienceLevel = experienceLevel,
                            cvUri = cvUri?.toString(),
                            portfolioUri = portfolioUri?.toString(),
                            jobTitle = jobTitle ?: ""
                        )
                        applicationViewModel.submitApplication(application)
                        Toast.makeText(context, "Lamaran berhasil disimpan!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }else {
                            Toast.makeText(context, "Mohon lengkapi semua field dan pastikan email valid.", Toast.LENGTH_SHORT).show()
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangePrimary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = "Lamar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
}

@Composable
fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF999999),
                    fontSize = 14.sp
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true
        )
    }
}

@Composable
fun FileUploadSection(
    label: String,
    fileName: String,
    isUploaded: Boolean,
    onClick: () -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = if (isUploaded) OrangePrimary else Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .clickable { onClick() }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = if (isUploaded) Icons.Default.CheckCircle else Icons.Default.Add,
                    contentDescription = null,
                    tint = if (isUploaded) OrangePrimary else Color(0xFF999999),
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = fileName,
                    fontSize = 14.sp,
                    color = if (isUploaded) OrangePrimary else Color(0xFF999999),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

private fun validateForm(
    fullName: String,
    phoneNumber: String,
    email: String,
    location: String,
    education: String,
    major: String,
    experienceLevel: String
): Boolean {
    return fullName.isNotBlank() &&
            phoneNumber.isNotBlank() &&
            email.isNotBlank() &&
            location.isNotBlank() &&
            education.isNotBlank() &&
            major.isNotBlank() &&
            experienceLevel.isNotBlank() &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFBF9, widthDp = 400, heightDp = 900)
@Composable
fun LamarPreview() {
    MaterialTheme {
        Lamar(
            navController = rememberNavController(),
            jobTitle = "Frontend Developer"
        )
    }
}