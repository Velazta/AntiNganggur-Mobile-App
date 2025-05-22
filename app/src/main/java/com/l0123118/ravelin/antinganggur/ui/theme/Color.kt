package com.l0123118.ravelin.antinganggur.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightPinkBackground = Color(0xFFFFF8F6)
val TextFieldGray = Color(0xFFF0F0F0)
val OrangePrimary = Color(0xFFFD7A5A)
val PinkSecondary = Color(0xFFE8518F)
val TextColorPrimary = Color(0xFF333333)
val TextColorSecondary = Color.Gray
val TextFieldBackground = Color(0xFFF0F0F0)
val AppOrange = Color(0xFFFF6F48)
val AppMagenta = Color(0xFFBD3B9F)
val TextGray = Color(0xFF8A8A8A)
val BannerGradientStart = Color(0xFFF9774E)
val BannerGradientEnd = Color(0xFFD43D92)
val CheckboxRed = Color(0xFFEA4335)
// Warna dari UI Anda
val AntiNganggurOrange = Color(0xFFF46036) // Perkiraan warna dari gambar
val AntiNganggurDarkGray = Color(0xFF5A5A5A) // Perkiraan warna dari gambar

//// --- Bagian Form Kontak dengan Background Gambar ---
//item {
//    // Menggunakan ConstraintLayout untuk menata elemen di atas background
//    // Tinggi Box ini akan menyesuaikan dengan content ConstraintLayout
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            // .height(700.dp) // Anda bisa set tinggi fix atau biarkan wrap content
//            .background(YellowBackgroundForm) // Warna background kuning
//    ) {
//        // Background Image
//        Image(
//            painter = painterResource(id = R.drawable.contact_form_background), // GANTI DENGAN GAMBAR BACKGROUND ANDA
//            contentDescription = "Contact Form Background",
//            contentScale = ContentScale.Crop, // Atau FillBounds, sesuaikan
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(LocalDensity.current.run { 2400.dp / 3 }) // Perkiraan tinggi gambar
//                .align(Alignment.TopCenter) // Posisikan gambar agar bagian atas terlihat
//        )
//
//        // Konten Form di atas gambar, menggunakan Card untuk tampilan panel putih
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 180.dp, start = 20.dp, end = 20.dp, bottom = 30.dp) // Atur padding agar card muncul di tengah gambar
//                .align(Alignment.TopCenter), // Agar card berada di atas gambar (jika gambar lebih pendek)
//            shape = RoundedCornerShape(16.dp),
//            colors = CardDefaults.cardColors(containerColor = Color.White),
//            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//        ) {
//            // ConstraintLayout untuk form di dalam Card
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(24.dp)
//            ) {
//                val (titleRef, descRef, nameLabelRef, nameFieldRef, emailLabelRef, emailFieldRef, messageLabelRef, messageFieldRef, submitButtonRef) = createRefs()
//
//                Text(
//                    text = "Hubungi Kami Sekarang",
//                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = DarkText),
//                    modifier = Modifier.constrainAs(titleRef) {
//                        top.linkTo(parent.top)
//                        start.linkTo(parent.start)
//                    }
//                )
//
//                Text(
//                    text = "Kami senang membantu Anda dengan pertanyaan Anda! Silahkan isi formulir di bawah ini dan kami akan segera menghubungi Anda kembali.",
//                    style = MaterialTheme.typography.bodyMedium.copy(color = MediumGrayText),
//                    modifier = Modifier
//                        .constrainAs(descRef) {
//                            top.linkTo(titleRef.bottom, margin = 8.dp)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                            width = Dimension.fillToConstraints // Agar text wrap
//                        }
//                        .padding(bottom = 20.dp)
//                )
//
//                Text(
//                    text = "Nama",
//                    style = MaterialTheme.typography.labelMedium.copy(color = DarkText, fontWeight = FontWeight.SemiBold),
//                    modifier = Modifier.constrainAs(nameLabelRef) {
//                        top.linkTo(descRef.bottom, margin = 16.dp)
//                        start.linkTo(parent.start)
//                    }
//                )
//                OutlinedTextField(
//                    value = nameState,
//                    onValueChange = { nameState = it },
//                    placeholder = { Text("Nama", color = MediumGrayText.copy(alpha=0.7f)) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .constrainAs(nameFieldRef) {
//                            top.linkTo(nameLabelRef.bottom, margin = 4.dp)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        },
//                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = OrangePeel,
//                        unfocusedBorderColor = LightGrayBorder,
//                    )
//                )
//
//                Text(
//                    text = "Email",
//                    style = MaterialTheme.typography.labelMedium.copy(color = DarkText, fontWeight = FontWeight.SemiBold),
//                    modifier = Modifier.constrainAs(emailLabelRef) {
//                        top.linkTo(nameFieldRef.bottom, margin = 16.dp)
//                        start.linkTo(parent.start)
//                    }
//                )
//                OutlinedTextField(
//                    value = emailState,
//                    onValueChange = { emailState = it },
//                    placeholder = { Text("Email", color = MediumGrayText.copy(alpha=0.7f)) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .constrainAs(emailFieldRef) {
//                            top.linkTo(emailLabelRef.bottom, margin = 4.dp)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        },
//                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = OrangePeel,
//                        unfocusedBorderColor = LightGrayBorder,
//                    )
//                )
//
//                Text(
//                    text = "Pesan",
//                    style = MaterialTheme.typography.labelMedium.copy(color = DarkText, fontWeight = FontWeight.SemiBold),
//                    modifier = Modifier.constrainAs(messageLabelRef) {
//                        top.linkTo(emailFieldRef.bottom, margin = 16.dp)
//                        start.linkTo(parent.start)
//                    }
//                )
//                OutlinedTextField(
//                    value = messageState,
//                    onValueChange = { messageState = it },
//                    placeholder = { Text("Tulis pesan anda disini", color = MediumGrayText.copy(alpha=0.7f)) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(120.dp) // Tinggi untuk area pesan
//                        .constrainAs(messageFieldRef) {
//                            top.linkTo(messageLabelRef.bottom, margin = 4.dp)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = OrangePeel,
//                        unfocusedBorderColor = LightGrayBorder,
//                    )
//                )
//
//                Button(
//                    onClick = {
//                        // TODO: Handle submit action
//                        println("Nama: $nameState, Email: $emailState, Pesan: $messageState")
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                        .constrainAs(submitButtonRef) {
//                            top.linkTo(messageFieldRef.bottom, margin = 24.dp)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                            bottom.linkTo(parent.bottom) // Opsional, jika ingin button selalu di bawah
//                        },
//                    colors = ButtonDefaults.buttonColors(containerColor = BlueSubmit),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text("Submit", color = Color.White, fontWeight = FontWeight.Bold)
//                }
//            }
//        }
//    }
//}
//}
//}