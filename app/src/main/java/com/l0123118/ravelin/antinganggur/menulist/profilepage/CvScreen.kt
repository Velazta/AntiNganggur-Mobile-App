package com.l0123118.ravelin.antinganggur.menulist.profilepage

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import java.io.InputStream
import java.io.OutputStream
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.MyApplication
import com.l0123118.ravelin.antinganggur.data.api.model.Cv
import com.l0123118.ravelin.antinganggur.di.ViewModelFactory
import com.l0123118.ravelin.antinganggur.ui.profile.CvUiState
import com.l0123118.ravelin.antinganggur.ui.profile.ProfileViewModel
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CvScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory((LocalContext.current.applicationContext as MyApplication).appContainer.profileRepository)
    )
) {
    val uiState by viewModel.cvState.collectAsState()
    val isUploading by viewModel.isUploading.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchUserCvs()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Curriculum Vitae (CV)", fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Kembali") } }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            UploadCvSection(
                isUploading = isUploading,
                onFileSelected = { uri ->
                    // Logika untuk mengubah URI menjadi MultipartBody.Part dan memanggil ViewModel
                    uri?.let {
                        val filePart = it.toMultipartBodyPart(context, "cv_file")
                        filePart?.let { part -> viewModel.uploadCv(part) }
                    }
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text("CV Tersimpan", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            when (val state = uiState) {
                is CvUiState.Loading -> Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                is CvUiState.Success -> {
                    if (state.cvs.isEmpty()) {
                        Text("Anda belum mengunggah CV.", color = Color.Gray, modifier = Modifier.padding(16.dp))
                    } else {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(state.cvs) { cv ->
                                CvItem(
                                    cv = cv,
                                    viewModel = viewModel,
                                    onDeleteClick = { viewModel.deleteCv(cv.id) }
                                )
                            }
                        }
                    }
                }
                is CvUiState.Error -> Text(state.message, color = Color.Red)
            }
        }
    }
}

@Composable
fun UploadCvSection(
    isUploading: Boolean,
    onFileSelected: (Uri?) -> Unit
) {
    // Launcher untuk memilih file dari penyimpanan perangkat
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onFileSelected(uri)
        }
    )

    Column {
        Text("Unggah CV Baru", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Unggah CV Anda dalam format PDF, DOC, atau DOCX (maksimal 5MB).", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(8.dp))
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Default.UploadFile, contentDescription = "Upload Icon", tint = Color.Gray, modifier = Modifier.size(48.dp))
                Text("Pilih file untuk diunggah", color = OrangePrimary, fontWeight = FontWeight.SemiBold)
                Text("atau seret dan lepas file di sini", color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        // Tentukan tipe file yang bisa dipilih
                        filePickerLauncher.launch("*/*")
                    },
                    enabled = !isUploading, // Nonaktifkan tombol saat sedang upload
                    colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
                ) {
                    if (isUploading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White, strokeWidth = 2.dp)
                    } else {
                        Text("Pilih File")
                    }
                }
            }
        }
    }
}

@Composable
fun CvItem(
    cv: Cv,
    viewModel: ProfileViewModel,
    onDeleteClick: () -> Unit,
) {
    val context = LocalContext.current

    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Description, contentDescription = "File Icon", tint = OrangePrimary)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(cv.originalName, fontWeight = FontWeight.SemiBold)
                Text("${cv.fileSizeKb} KB - Diunggah pada ${cv.uploadedAt}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            TextButton(
                onClick = {
                    viewModel.downloadCvFile(
                        context = context,
                        cv = cv,
                        onSuccess = { message ->
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        },
                        onError = { errorMessage ->
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            ) {
                Text("download")
            }
            IconButton(onClick = onDeleteClick) { Icon(Icons.Default.Delete, "Hapus", tint = Color.Red) }
        }
    }
}

fun Uri.toMultipartBodyPart(context: Context, partName: String): MultipartBody.Part? {
    return try {
        val contentResolver = context.contentResolver
        var fileName: String? = null
        contentResolver.query(this, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex != -1) {
                fileName = cursor.getString(nameIndex)
                }
            }
        }
        val inputStream = contentResolver.openInputStream(this) ?: return null
        val fileBytes = inputStream.readBytes()
        val requestBody = fileBytes.toRequestBody(
            contentResolver.getType(this)?.toMediaTypeOrNull()
        )
        return MultipartBody.Part.createFormData(partName, fileName ?: "cv_file.pdf", requestBody)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Preview(showBackground = true)
@Composable
fun previewCvScreen()
{
    ANTINGANGGURTheme {
        CvScreen(navController = rememberNavController())
    }
}
