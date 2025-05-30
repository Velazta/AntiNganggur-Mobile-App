package com.l0123118.ravelin.antinganggur.authentification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.ui.theme.TextColorPrimary
import com.l0123118.ravelin.antinganggur.ui.theme.LightPinkBackground
import com.l0123118.ravelin.antinganggur.ui.theme.TextFieldGray
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary
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
                SignInPage2(navController = rememberNavController())
            }
        }
    }
}


@Composable
fun SignInPage2(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current

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
                onClick = {
                    navigateToMainActivity(context)
                },
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
            onClick = {
                navigateToMainActivity(context)
            },
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
                        navigateToLogin(context)
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

fun navigateToLogin(context: Context) {
    val intent = Intent(context, Login::class.java)
    context.startActivity(intent)

    context.startActivity(intent)
    try {
        val intent = Intent(context, Login::class.java)
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("NavigationError", "Error navigating to Login: ${e.message}")
        Toast.makeText(context, "Navigation failed to Login", Toast.LENGTH_SHORT).show()
    }
}




@Preview(showBackground = true, widthDp = 360, heightDp = 900) // Sesuaikan dengan dimensi target (1080x2400px adalah ~360x800dp)
@Composable
fun SignInScreenPreview2() {
    ANTINGANGGURTheme {
        SignInPage2(navController = rememberNavController())
    }
}