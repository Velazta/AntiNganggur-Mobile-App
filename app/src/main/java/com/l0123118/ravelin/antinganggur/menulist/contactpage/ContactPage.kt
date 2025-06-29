package com.l0123118.ravelin.antinganggur.menulist.contactpage


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme

class Contact : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANTINGANGGURTheme {
                ContactPage(navController = rememberNavController())
            }
        }
    }
}

val OrangePeel = Color(0xFFFF7144)
val LightPeach = Color(0xFFFFF6F4)
val DarkText = Color(0xFF333333)
val MediumGrayText = Color(0xFF757575)
val LightGrayBorder = Color(0xFFE0E0E0)
val BlueSubmit = Color(0xFF1A73E8)
val YellowBackgroundForm = Color(0xFFFFD180)


//CODE 1
@Composable
fun ContactPage(navController: NavHostController) {
    var nameState by remember{ mutableStateOf("") }
    var emailState by remember{ mutableStateOf("") }
    var messageState by remember{ mutableStateOf("") }
    val context = LocalContext.current

    val openInstagram: () -> Unit = {
        val uri = Uri.parse("https://www.instagram.com/ravelinluth/")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val openGitHub: () -> Unit = {
        val uri = Uri.parse("https://github.com/Velazta/AntiNganggur-Mobile-App")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

    val openWhatsApp: () -> Unit = {
        val phoneNumber = "6281219500363"
        val message = "Halo, saya tertarik dengan layanan Anda."
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val socialMediaItems = listOf(
        SocialMediaItem(R.drawable.instagramb, R.drawable.instagramw, "Instagram", openInstagram),
        SocialMediaItem(R.drawable.whatsappb, R.drawable.whatsappw, "WhatsApp", openWhatsApp),
        SocialMediaItem(R.drawable.githubb, R.drawable.githubw, "GitHub", openGitHub)
    )


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPeach)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "(OUR CONTACT)",
                    style = MaterialTheme.typography.bodySmall.copy(MediumGrayText),
                    fontSize = 12.sp
                )
                Text(
                    text = "Get Ready",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    ),
                    fontSize = 38.sp
                )
                Text(
                    text = "With Us",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = OrangePeel
                    ),
                    fontSize = 38.sp,
                    modifier = Modifier.padding(top = 0.dp)
                )
            }
        }

        item {
            val context = LocalContext.current
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(12.dp),
                        clip = false
                    )
                    .clickable{
                        var mapUri = Uri.parse("https://maps.app.goo.gl/x5YD3bQ5qcEAph3J7")
                        val intent = Intent(Intent.ACTION_VIEW, mapUri)
                        context.startActivity(intent)
                    }
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        color = OrangePeel,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id= R.drawable.map),
                    contentDescription = "LocationMap",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(height = 200.dp, width = 200.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }

        item{
            ContactInfoItem(
                icon = Icons.Outlined.LocationOn,
                title = "Location",
                details = listOf(
                    "Jalan Ir. Sutami 36-A, Kentingan,",
                    "Jebres, Surakarta, Jawa Tengah,",
                    "Indonesia 57126"
                )
            )
        }

        item{
            ContactInfoItem(
                icon = Icons.Outlined.Call,
                title = "Contact Person",
                details = listOf(
                    "+62 812 1950 0363"
                )
            )
        }

        item {
            ContactInfoItem(
                icon = Icons.Outlined.AccessTime,
                title = "Working Hours",
                details = listOf("Senin - Sabtu: 07:00-18:00")
            )
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(socialMediaItems.size) { index ->
                    val item = socialMediaItems[index]
                    SocialMediaButton(
                        normalImageRes = item.normalImageRes,
                        pressedImageRes = item.pressedImageRes,
                        text = item.text,
                        onClick = item.onClickAction,
                        Modifier.width(140.dp)
                    )
                }
            }
            Spacer(modifier = Modifier
                .height(50.dp)
                .padding(horizontal = 24.dp)
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(YellowBackgroundForm)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.girl),
                    contentDescription = "Contact Form Bg",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(400.dp)
                        .height(LocalDensity.current.run { 2400.dp / 3 })
                        .align(Alignment.TopCenter)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 60.dp,
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 30.dp
                        )
                        .align(Alignment.TopCenter),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    //Constraint Layout
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 20.dp, start = 15.dp, end = 15.dp)
                    ) {
                        val (
                            titleRef,
                            descRef,
                            nametitle,
                            nameFieldRef,
                            emailtitle,
                            emailFieldRef,
                            messagetitle,
                            messageFieldRef,
                            submitButtonRef
                        ) = createRefs()

                        Text(
                            text = "Hubungi Kami Sekarang",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = DarkText
                            ),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .constrainAs(titleRef) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                }
                        )
                        
                        Text(
                            text = "Kami senang membantu Anda dengan pertanyaan Anda! Silahkan isi formulir di bawah ini dan kami akan segera menghubungi Anda kembali.",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MediumGrayText),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .constrainAs(descRef) {
                                    top.linkTo(titleRef.bottom, margin = 10.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        Text(
                            text = "Nama",
                            fontSize = 15.sp,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = DarkText,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .constrainAs(nametitle) {
                                    top.linkTo(descRef.bottom, margin = 10.dp)
                                    start.linkTo(parent.start)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        OutlinedTextField(
                            value = nameState,
                            onValueChange = { nameState = it },
                            placeholder = { Text("Nama", color = MediumGrayText.copy(alpha=0.7f)) },
                            shape = RoundedCornerShape(12),
                            modifier = Modifier
                                .fillMaxWidth()
                                .constrainAs(nameFieldRef) {
                                    top.linkTo(nametitle.bottom, margin = 4.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = OrangePeel,
                                unfocusedBorderColor = LightGrayBorder,
                            )
                        )

                        Text(
                            text = "Email",
                            fontSize = 15.sp,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = DarkText,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .constrainAs(emailtitle) {
                                    top.linkTo(nameFieldRef.bottom, margin = 10.dp)
                                    start.linkTo(parent.start)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        OutlinedTextField(
                            value = emailState,
                            onValueChange = { emailState = it },
                            placeholder = { Text("Email", color = MediumGrayText.copy(alpha=0.7f)) },
                            shape = RoundedCornerShape(12),
                            modifier = Modifier
                                .fillMaxWidth()
                                .constrainAs(emailFieldRef) {
                                    top.linkTo(emailtitle.bottom, margin = 4.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = OrangePeel,
                                unfocusedBorderColor = LightGrayBorder,
                            )
                        )

                        Text(
                            text = "Pesan",
                            fontSize = 15.sp,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = DarkText,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .constrainAs(messagetitle) {
                                    top.linkTo(emailFieldRef.bottom, margin = 10.dp)
                                    start.linkTo(parent.start)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        OutlinedTextField(
                            value = messageState,
                            onValueChange = { messageState = it },
                            placeholder = { Text("Tulis pesan anda disini", color = MediumGrayText.copy(alpha=0.7f)) },
                            shape = RoundedCornerShape(12),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .constrainAs(messageFieldRef) {
                                    top.linkTo(messagetitle.bottom, margin = 4.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = OrangePeel,
                                unfocusedBorderColor = LightGrayBorder,
                            )
                        )

                        Button(
                            onClick = { /*TODO : BUTTON TO SEND MESSAGE*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .constrainAs(submitButtonRef) {
                                    top.linkTo(messageFieldRef.bottom, margin = 24.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                },
                            colors = ButtonDefaults.buttonColors(containerColor = BlueSubmit),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Submit", color = Color.White, fontWeight = FontWeight.Bold)
                        }

                    }
                }
            }
        }
    }
}

data class SocialMediaItem(
    val normalImageRes: Int,
    val pressedImageRes: Int,
    val text: String,
    val onClickAction: () -> Unit
)

@Composable
fun SocialMediaButton(
    normalImageRes: Int,
    pressedImageRes: Int,

    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = if (isPressed) OrangePeel else Color.Transparent
    val contentColor = if (isPressed) Color.White else DarkText
    val borderColor = if (isPressed) OrangePeel else LightGrayBorder

    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp), // Adjust height as per need
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            width = 1.5.dp,
            brush = SolidColor(borderColor)
        ),
        interactionSource = interactionSource
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val imageToShow = if (isPressed) pressedImageRes else normalImageRes
            Image(
                painter = painterResource(id = imageToShow),
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )

            //            Icon(
//                imageVector = icon,
//                contentDescription = text,
//                tint = DarkText,
//                modifier = Modifier.size(18.dp)
//            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = contentColor
            )
        }
    }
}

@Composable
fun ContactInfoItem(icon: ImageVector,title: String, details: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
//        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = OrangePeel,
            modifier = Modifier
                .size(36.dp)
                .background(OrangePeel.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column() {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(MediumGrayText),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 6.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            details.forEach { detail ->
                Text(
                    text = detail,
                    style = MaterialTheme.typography.bodyLarge.copy(OrangePeel),
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 2000)
@Composable
fun ContactPagePreview() {
    ANTINGANGGURTheme {
        ContactPage(navController = rememberNavController())
    }
}

