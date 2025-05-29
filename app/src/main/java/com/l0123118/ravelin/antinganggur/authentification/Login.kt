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
import com.l0123118.ravelin.antinganggur.MainActivity
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
import com.l0123118.ravelin.antinganggur.ui.theme.CheckboxRed
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANTINGANGGURTheme {
                LoginPage(navController = rememberNavController())
            }
        }
    }
}

@Composable
fun LoginPage(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .background(LightPinkBackground)
            .verticalScroll(rememberScrollState())
//            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        val (
            bannerBox,
            logoGroup,
            logInTitle,
            usernameField,
            usernameLabel,
            passwordField,
            passwordLabel,
            rememberMeRow,
            logInButton,
            signUp
        ) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(bannerBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(screenHeight * 0.35f) // 30% of the screen height
                }
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(BannerGradientStart,BannerGradientEnd)))

        ) {
            Image(
                painter = painterResource(id = R.drawable.loginilu),
                contentDescription = "Banner Illustration",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .align(Alignment.BottomCenter)
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
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(30.dp)
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
            text = "Log in",
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier
                .constrainAs(logInTitle) {
                    top.linkTo(logoGroup.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                }
        )

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var rememberMe by remember { mutableStateOf(false) }

        Text(
            text = "Email",
            style = MaterialTheme.typography.bodyMedium,
            color = TextColorPrimary,
            modifier = Modifier.constrainAs(usernameLabel) {
                top.linkTo(logInTitle.bottom, margin = 24.dp)
                start.linkTo(parent.start, margin = 32.dp)
            }
        )

        // Full Name TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Enter your email") },
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
                .constrainAs(usernameField) {
                    top.linkTo(usernameLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = "Password",
            style = MaterialTheme.typography.bodyMedium,
            color = TextColorPrimary,
            modifier = Modifier.constrainAs(passwordLabel) {
                top.linkTo(usernameField.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 32.dp)
            }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Enter your password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(50),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = TextFieldGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = TextFieldGray,
                disabledContainerColor = TextFieldGray,
                cursorColor = OrangePrimary
            ),
            modifier = Modifier
                .constrainAs(passwordField) {
                    top.linkTo(passwordLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Row(
            modifier = Modifier
                .constrainAs(rememberMeRow) {
                    top.linkTo(passwordField.bottom, margin = 16.dp) // Adjusted margin slightly
                    start.linkTo(parent.start, margin = 28.dp) // Adjusted margin for checkbox alignment
                }
                .clickable { rememberMe = !rememberMe } // Makes the whole row clickable
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { isChecked -> rememberMe = isChecked },
                colors = CheckboxDefaults.colors(
                    checkedColor = CheckboxRed, // Your desired color for the checked state
                    uncheckedColor = TextGray, // Or OrangePrimary.copy(alpha = 0.6f) or any other color
                    checkmarkColor = Color.White // Color of the check mark
                ),
                modifier = Modifier.size(24.dp) // Control the size of the checkbox
            )
            Spacer(modifier = Modifier.width(6.dp)) // Reduced spacer as Checkbox has some intrinsic padding

            Text(
                text = "Remember me",
                fontSize = 14.sp,
                color = TextColorPrimary
            )
        }

        Button(
            onClick = {
                navigateToMainActivity(context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(logInButton) {
                    top.linkTo(rememberMeRow.bottom, margin = 24.dp)
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
                        shape = RoundedCornerShape(50.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Log In",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }

        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = TextGray, fontSize = 14.sp, fontWeight = FontWeight.Normal)) {
                append("Don't have an account? ")
            }
            pushStringAnnotation(tag = "SIGN_IN", annotation = "sign_in_action")

            withStyle(style = SpanStyle(color = AppOrange, fontSize = 14.sp, fontWeight = FontWeight.Medium)) {
                append("Sign In")
            }
            pop()
        }

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(tag = "SIGN_IN", start = offset, end = offset)
                    .firstOrNull()?.let {
                        navigateToSignUp(context)
                    }
            },
            modifier = Modifier
                .constrainAs(signUp) {
                    top.linkTo(logInButton.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = -30.dp)
                }
        )
    }
}

fun navigateToSignUp(context: Context) {
    val intent = Intent(context, SignIn::class.java)
    context.startActivity(intent)
    try {
        val intent = Intent(context, SignIn::class.java)
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e("NavigationError", "Error navigating to SignIn: ${e.message}")
        Toast.makeText(context, "Navigation failed to SignIn", Toast.LENGTH_SHORT).show()
    }
}

// Function to navigate to MainActivity
fun navigateToMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

@Preview(showBackground = true, widthDp = 360, heightDp = 900)
@Composable
fun LoginPreview() {
    LoginPage(navController = rememberNavController())
}