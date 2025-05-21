package com.l0123118.ravelin.antinganggur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Menggunakan autoMirrored untuk RTL support
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import com.l0123118.ravelin.antinganggur.ui.theme.TextColorPrimary
import com.l0123118.ravelin.antinganggur.ui.theme.LightPinkBackground
import com.l0123118.ravelin.antinganggur.ui.theme.TextFieldGray
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
import com.l0123118.ravelin.antinganggur.ui.theme.PinkSecondary
import com.l0123118.ravelin.antinganggur.ui.theme.TextColorSecondary
import com.l0123118.ravelin.antinganggur.ui.theme.TextFieldBackground
import com.l0123118.ravelin.antinganggur.ui.theme.AppOrange
import com.l0123118.ravelin.antinganggur.ui.theme.AppMagenta
import com.l0123118.ravelin.antinganggur.ui.theme.TextGray
import com.l0123118.ravelin.antinganggur.ui.theme.BannerGradientStart
import com.l0123118.ravelin.antinganggur.ui.theme.BannerGradientEnd
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme

class SignIn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANTINGANGGURTheme {
                SignInPage2()
            }
        }
    }
}




//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SignInPage(
//    onBackClicked: () -> Unit = {},
//    onSignInClicked: (String, String, String) -> Unit = { _, _, _ -> },
//    onLogInClicked: () -> Unit = {}
//) {
//    var fullName by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = LightPinkBackground
//    ) {
//        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//            val (
//                headerImage, backButton,
//                logo, appNameText,
//                titleText,
//                fullNameLabel, fullNameField,
//                emailLabel, emailField,
//                passwordLabel, passwordField,
//                signInButton,
//                alreadyAccountText
//            ) = createRefs()
//
//            // Header Image
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_foreground), // GANTI DENGAN ASET ANDA
//                contentDescription = "Header Illustration",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(280.dp) // Sesuaikan tinggi sesuai kebutuhan
//                    .constrainAs(headerImage) {
//                        top.linkTo(parent.top)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }
//            )
//
//            // Back Button
//            IconButton(
//                onClick = onBackClicked,
//                modifier = Modifier.constrainAs(backButton) {
//                    top.linkTo(parent.top, margin = 16.dp)
//                    start.linkTo(parent.start, margin = 8.dp)
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "Back",
//                    tint = Color.White // Warna ikon di atas gambar header
//                )
//            }
//
//            // Logo
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_foreground), // GANTI DENGAN ASET ANDA
//                contentDescription = "App Logo",
//                modifier = Modifier
//                    .size(40.dp)
//                    .constrainAs(logo) {
//                        top.linkTo(headerImage.bottom, margin = 24.dp)
//                        start.linkTo(parent.start, margin = 32.dp)
//                    }
//            )
//
//            // App Name Text
//            Text(
//                text = "AntiNganggur",
//                color = OrangePrimary,
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp,
//                modifier = Modifier.constrainAs(appNameText) {
//                    start.linkTo(logo.end, margin = 8.dp)
//                    top.linkTo(logo.top)
//                    bottom.linkTo(logo.bottom)
//                }
//            )
//
//            // Title "Sign In"
//            Text(
//                text = "Sign In",
//                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Medium),
//                color = TextColorPrimary,
//                modifier = Modifier.constrainAs(titleText) {
//                    top.linkTo(logo.bottom, margin = 16.dp)
//                    start.linkTo(parent.start, margin = 32.dp)
//                }
//            )
//
//            // Full Name Label
//            Text(
//                text = "Full Name",
//                style = MaterialTheme.typography.bodyMedium,
//                color = TextColorSecondary,
//                modifier = Modifier.constrainAs(fullNameLabel) {
//                    top.linkTo(titleText.bottom, margin = 24.dp)
//                    start.linkTo(parent.start, margin = 32.dp)
//                }
//            )
//
//            // Full Name TextField
//            OutlinedTextField(
//                value = fullName,
//                onValueChange = { fullName = it },
//                placeholder = { Text("Enter your full name") },
//                singleLine = true,
//                shape = RoundedCornerShape(50), // Rounded corners
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = OrangePrimary,
//                    unfocusedBorderColor = TextFieldGray,
//                    focusedContainerColor = Color.White,
//                    unfocusedContainerColor = TextFieldGray,
//                    disabledContainerColor = TextFieldGray,
//                ),
//                modifier = Modifier
//                    .constrainAs(fullNameField) {
//                        top.linkTo(fullNameLabel.bottom, margin = 8.dp)
//                        start.linkTo(parent.start, margin = 32.dp)
//                        end.linkTo(parent.end, margin = 32.dp)
//                        width = Dimension.fillToConstraints
//                    }
//            )
//
//            // Email Label
//            Text(
//                text = "Email",
//                style = MaterialTheme.typography.bodyMedium,
//                color = TextColorSecondary,
//                modifier = Modifier.constrainAs(emailLabel) {
//                    top.linkTo(fullNameField.bottom, margin = 16.dp)
//                    start.linkTo(parent.start, margin = 32.dp)
//                }
//            )
//
//            // Email TextField
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                placeholder = { Text("Enter your email") },
//                singleLine = true,
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                shape = RoundedCornerShape(50),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = OrangePrimary,
//                    unfocusedBorderColor = TextFieldGray,
//                    focusedContainerColor = Color.White,
//                    unfocusedContainerColor = TextFieldGray,
//                    disabledContainerColor = TextFieldGray,
//                ),
//                modifier = Modifier
//                    .constrainAs(emailField) {
//                        top.linkTo(emailLabel.bottom, margin = 8.dp)
//                        start.linkTo(parent.start, margin = 32.dp)
//                        end.linkTo(parent.end, margin = 32.dp)
//                        width = Dimension.fillToConstraints
//                    }
//            )
//
//            // Password Label
//            Text(
//                text = "Password",
//                style = MaterialTheme.typography.bodyMedium,
//                color = TextColorSecondary,
//                modifier = Modifier.constrainAs(passwordLabel) {
//                    top.linkTo(emailField.bottom, margin = 16.dp)
//                    start.linkTo(parent.start, margin = 32.dp)
//                }
//            )
//
//            // Password TextField
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                placeholder = { Text("Enter your password") },
//                singleLine = true,
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                shape = RoundedCornerShape(50),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = OrangePrimary,
//                    unfocusedBorderColor = TextFieldGray,
//                    focusedContainerColor = Color.White,
//                    unfocusedContainerColor = TextFieldGray,
//                    disabledContainerColor = TextFieldGray,
//                ),
//                modifier = Modifier
//                    .constrainAs(passwordField) {
//                        top.linkTo(passwordLabel.bottom, margin = 8.dp)
//                        start.linkTo(parent.start, margin = 32.dp)
//                        end.linkTo(parent.end, margin = 32.dp)
//                        width = Dimension.fillToConstraints
//                    }
//            )
//
//            // Sign In Button
//            Button(
//                onClick = { onSignInClicked(fullName, email, password) },
//                shape = RoundedCornerShape(50),
//                contentPadding = PaddingValues(), // Penting untuk gradient agar mengisi seluruh button
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Container transparan
//                modifier = Modifier
//                    .constrainAs(signInButton) {
//                        top.linkTo(passwordField.bottom, margin = 32.dp)
//                        start.linkTo(parent.start, margin = 32.dp)
//                        end.linkTo(parent.end, margin = 32.dp)
//                        width = Dimension.fillToConstraints
//                    }
//                    .height(50.dp) // Tinggi button
//                    .background(
//                        brush = Brush.horizontalGradient(colors = listOf(OrangePrimary, PinkSecondary)),
//                        shape = RoundedCornerShape(50)
//                    )
//            ) {
//                Text(
//                    text = "Sign In",
//                    color = Color.White,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            // "Already have an account? Log In" Text
//            val annotatedString = buildAnnotatedString {
//                append("Already have an account? ")
//                withStyle(style = SpanStyle(color = OrangePrimary, fontWeight = FontWeight.Bold)) {
//                    pushStringAnnotation(tag = "LOGIN", annotation = "login")
//                    append("Log In")
//                    pop()
//                }
//            }
//
//            ClickableText(
//                text = annotatedString,
//                style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
//                onClick = { offset ->
//                    annotatedString.getStringAnnotations(tag = "LOGIN", start = offset, end = offset)
//                        .firstOrNull()?.let {
//                            onLogInClicked()
//                        }
//                },
//                modifier = Modifier.constrainAs(alreadyAccountText) {
//                    top.linkTo(signInButton.bottom, margin = 24.dp)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    bottom.linkTo(parent.bottom, margin = 24.dp, goneMargin = 16.dp) // agar ada jarak jika keyboard muncul
//                }
//            )
//
//        }
//    }
//}

//@Preview(showBackground = true, widthDp = 360, heightDp = 800) // Sesuaikan dengan dimensi target (1080x2400px adalah ~360x800dp)
//@Composable
//fun SignInScreenPreview() {
//    SignInPage()
//}

@Composable
fun SignInPage2() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPinkBackground)
            .verticalScroll(rememberScrollState())
//            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        val (
            bannerBox,
            logoGroup,
            signInTitle,
            fullNameField,
            fullNameLabel,
            emailField,
            emailLabel,
            passwordField,
            passwordLabel,
            signInButton,
            loginText
        ) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(bannerBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(screenHeight * 0.35f)
                }
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(BannerGradientStart, BannerGradientEnd)))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logosignin),
                contentDescription = "Banner Illustration",
                modifier = Modifier
                    .height(240.dp)
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { /* TODO: Handle back navigation */ },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 60.dp, start = 16.dp)
                    .background(Color.Black.copy(alpha = 0.2f), CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(logoGroup) {
                    top.linkTo(bannerBox.bottom, margin = 28.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.wrapContent
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoantinganggur),
                    contentDescription = "Logo Icon",
                    modifier = Modifier
                        .size(30.dp)
//                        .padding(top = 8.dp)
                )

                Row(
                    modifier = Modifier.padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Anti",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Nganggur",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = AppOrange
                    )
                }
            }
        }

        Text(
            text = "Sign In",
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier.constrainAs(signInTitle) {
                top.linkTo(logoGroup.bottom, margin = 32.dp)
                start.linkTo(parent.start, margin = 32.dp)
            }
        )

        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        // Full Name Label
        Text(
            text = "Full Name",
            style = MaterialTheme.typography.bodyMedium,
            color = TextColorPrimary,
            modifier = Modifier.constrainAs(fullNameLabel) {
                top.linkTo(signInTitle.bottom, margin = 24.dp)
                start.linkTo(parent.start, margin = 32.dp)
            }
        )

        // Full Name TextField
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = { Text("Enter your full name") },
            singleLine = true,
            shape = RoundedCornerShape(50), // Rounded corners
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = TextFieldGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = TextFieldGray,
                disabledContainerColor = TextFieldGray,
            ),
            modifier = Modifier
                .constrainAs(fullNameField) {
                    top.linkTo(fullNameLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    width = Dimension.fillToConstraints
                }
        )

        // Email Label
        Text(
            text = "Email",
            style = MaterialTheme.typography.bodyMedium,
            color = TextColorPrimary,
            modifier = Modifier.constrainAs(emailLabel) {
                top.linkTo(fullNameField.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 32.dp)
            }
        )

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Enter your email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(50),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = TextFieldGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = TextFieldGray,
                disabledContainerColor = TextFieldGray,
            ),
            modifier = Modifier
                .constrainAs(emailField) {
                    top.linkTo(emailLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    width = Dimension.fillToConstraints
                }
        )

        // Password Label
        Text(
            text = "Password",
            style = MaterialTheme.typography.bodyMedium,
            color = TextColorPrimary,
            modifier = Modifier.constrainAs(passwordLabel) {
                top.linkTo(emailField.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 32.dp)
            }
        )

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Enter your password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(50),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = TextFieldGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = TextFieldGray,
                disabledContainerColor = TextFieldGray,
            ),
            modifier = Modifier
                .constrainAs(passwordField) {
                    top.linkTo(passwordLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Button(
            onClick = { /*TODO: Handle sign in button click*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(signInButton) {
                    top.linkTo(passwordField.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    width = Dimension.fillToConstraints
                },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(AppOrange, AppMagenta)
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Sign In",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }

        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = TextGray, fontSize = 14.sp, fontWeight = FontWeight.Normal)) {
                append("Already have an account? ")
            }
            pushStringAnnotation(tag = "LOGIN", annotation = "login_action")
            withStyle(style = SpanStyle(color = AppOrange, fontWeight = FontWeight.Bold, fontSize = 14.sp)) {
                append("Log In")
            }
            pop()
        }

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(tag = "LOGIN", start = offset, end = offset)
                    .firstOrNull()?.let {
                        // TODO: NAVIGATE TO LOGIN PAGE
                    }
            },
            modifier = Modifier
                .constrainAs(loginText) {
                    top.linkTo(signInButton.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                }
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 900) // Sesuaikan dengan dimensi target (1080x2400px adalah ~360x800dp)
@Composable
fun SignInScreenPreview2() {
    ANTINGANGGURTheme {
        SignInPage2()
    }
}