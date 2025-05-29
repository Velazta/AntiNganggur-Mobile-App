import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme

//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowForward
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.constraintlayout.compose.Dimension
//import com.l0123118.ravelin.antinganggur.R
//
////SECTION FOR TEAM MEMBER
//
//@Composable
//fun TeamMemberCardScreen() {
//    val orangeColor = Color(0xFFFF6F3E)
//    val darkBlueTextColor = Color(0xFF0D47A1)
//    val creamBackgroundColor = Color(0xFFFFF9F5)
//    val darkTextColor = Color(0xFF333333)
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(creamBackgroundColor)
//            .padding(16.dp)
//
//    ) {
//        ConstraintLayout(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            val (ourTeamTitleBox, inspiringText, memberCard) = createRefs()
//
//            Box(
//                modifier = Modifier
//                    .constrainAs(ourTeamTitleBox) {
//                        top.linkTo(parent.top, margin = 20.dp)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }
//                    .background(orangeColor)
//                    .padding(horizontal = 24.dp, vertical = 8.dp)
//            ) {
//                Text(
//                    text = "OUR TEAM",
//                    color = Color.White,
//                    fontSize = 32.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Text(
//                text = "Inspiring change with motivational keynotes,\nWorkshop, and seminar for",
//                fontSize = 14.sp,
//                color = darkTextColor,
//                fontWeight = FontWeight.Medium,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .constrainAs(inspiringText) {
//                        top.linkTo(ourTeamTitleBox.bottom, margin = 12.dp)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        width = Dimension.wrapContent
//                    }
//                    .padding(horizontal = 16.dp)
//            )
//
//            Card(
//                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
//                shape = RoundedCornerShape(0.dp), // Bentuk Card utama
//                colors = CardDefaults.cardColors(containerColor = creamBackgroundColor),
//                modifier = Modifier
//                    .constrainAs(memberCard) {
//                        top.linkTo(inspiringText.bottom, margin = 20.dp)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        width = Dimension.fillToConstraints
//                    }
//                    .fillMaxWidth()
//            ) {
//                ConstraintLayout(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                    // Padding bawah untuk konten di dalam card, bukan untuk image section
//                ) {
//                    // Pisahkan referensi untuk orange background, image, dan text overlay
//                    val (orangeBackgroundShape, profileImage, textOverlaysOnImage, managerTitleRow, descriptionText, findOutMoreRow) = createRefs()
//
//                    // 1. Latar Belakang Oranye Bulat
//                    Box(
//                        modifier = Modifier
//                            .constrainAs(orangeBackgroundShape) {
//                                top.linkTo(parent.top, margin = 100.dp)
//                                start.linkTo(parent.start, margin = 35.dp)
//                                end.linkTo(parent.end, margin = 35.dp)
//                                width = Dimension.fillToConstraints
//                            }
//                            .fillMaxWidth()
//                            .height(220.dp) // Sesuaikan tinggi area oranye
//                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp)) // Bentuk bulat penuh untuk area oranye
//                            .background(orangeColor)
//                    )
//
//                    // 2. Gambar Wanita (idealnya PNG cutout)
//                    Image(
//                        painter = painterResource(id = R.drawable.robby), // GANTI DENGAN GAMBAR CUTOUT ANDA
//                        contentDescription = "Team Member",
//                        // Gunakan ContentScale.Fit agar seluruh gambar cutout terlihat dan tidak terpotong aneh
//                        // Jika gambar Anda bukan cutout, ContentScale.Crop mungkin lebih baik tapi akan memotong.
//                        contentScale = ContentScale.Fit,
//                        alignment = Alignment.BottomCenter, // Posisikan bagian bawah gambar di tengah
//                        modifier = Modifier
//                            .constrainAs(profileImage) {
//                                // Posisikan gambar relatif terhadap latar belakang oranye
//                                // Ini akan menempatkan bagian bawah tengah gambar di bagian bawah tengah kotak oranye
//                                // Anda bisa bermain dengan constraint dan margin untuk efek "keluar"
//                                bottom.linkTo(orangeBackgroundShape.bottom)
//                                centerHorizontallyTo(orangeBackgroundShape)
//                                // Lebar dan tinggi gambar bisa diatur agar 'keluar' dari orangeBackgroundShape jika perlu
//                                // Misalnya, jika gambar lebih tinggi dari orangeBackgroundShape
//                                height = Dimension.value(320.dp)// Sesuaikan tinggi gambar, bisa lebih tinggi dari orange bg
//                                width = Dimension.percent(0.85f) // Ambil 85% lebar orangeBackgroundShape
//                            }
//                    )
//
//                    // 3. Text Overlay di atas Gambar
//                    Column(
//                        modifier = Modifier
//                            .constrainAs(textOverlaysOnImage) {
//                                // Posisikan relatif terhadap gambar wanita
//                                bottom.linkTo(profileImage.bottom, margin = 20.dp)
//                                start.linkTo(profileImage.start, margin = 20.dp) // Atau parent.start dengan margin yg sesuai
//                                // Pastikan ini muncul di atas gambar jika ada tumpang tindih (ConstraintLayout menangani urutan render berdasarkan constraint)
//                            }
//                            .padding(start = 0.dp) // Hapus padding start dari sini jika sudah diatur oleh constraint ke profileImage.start
//                    ) {
//                        TextOverlay("Growing Future")
//                        Spacer(modifier = Modifier.height(4.dp))
//                        TextOverlay("Leader")
//                    }
//
//
//                    // Row untuk "Manager"
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .constrainAs(managerTitleRow) {
//                                // Ini sekarang harus di bawah orangeBackgroundShape atau profileImage, mana yang lebih rendah
//                                top.linkTo(orangeBackgroundShape.bottom, margin = 20.dp)
//                                start.linkTo(parent.start, margin = 35.dp)
//                                bottom.linkTo(descriptionText.top, margin = 0.dp) // Untuk menjaga jarak
//                            }
//                            .padding(top = 0.dp) // Hapus padding top jika sudah diatur oleh constraint
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Star,
//                            contentDescription = "Star Icon",
//                            tint = orangeColor,
//                            modifier = Modifier.size(35.dp)
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(
//                            text = "Manager",
//                            fontSize = 30.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = darkTextColor
//                        )
//                    }
//
//                    Text(
//                        text = "Improve well being, grow future leader, become the greatness human",
//                        fontSize = 20.sp,
//                        color = orangeColor,
//                        lineHeight = 30.sp,
//                        modifier = Modifier
//                            .constrainAs(descriptionText) {
//                                top.linkTo(managerTitleRow.bottom, margin = 10.dp)
//                                start.linkTo(parent.start, margin = 35.dp)
//                                end.linkTo(parent.end, margin = 80.dp)
//                                width = Dimension.fillToConstraints
//                                bottom.linkTo(findOutMoreRow.top, margin = 0.dp) // Untuk menjaga jarak
//                            }
//                    )
//
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .constrainAs(findOutMoreRow) {
//                                top.linkTo(descriptionText.bottom, margin = 10.dp)
//                                start.linkTo(parent.start, margin = 35.dp)
//                                bottom.linkTo(parent.bottom, margin = 20.dp) // Menempel ke bawah card
//                            }
//                            .clickable { /* Click click details*/}
//                    ) {
//                        Text(
//                            text = "Find out more",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = darkBlueTextColor
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
//                            contentDescription = "Arrow Forward",
//                            tint = darkBlueTextColor,
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//@Composable
//fun TextOverlay(text: String) {
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(8.dp))
//            .background(Color.Black.copy(alpha = 0.5f))
//            .padding(horizontal = 12.dp, vertical = 6.dp)
//    ) {
//        Text(
//            text = text,
//            color = Color.White,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold
//        )
//    }
//}






//// SECTION: DATA (Tidak ada ViewModel lagi)
//
//data class TeamMemberData(
//    val id: Int,
//    @DrawableRes val imageRes: Int,
//    val nameLine1: String,
//    val nameLine2: String,
//    val role: String,
//    val description: String
//)
//
//// Fungsi untuk mendapatkan data sampel, bisa dipanggil di mana saja
//fun getSampleTeamMembers(): List<TeamMemberData> {
//    return listOf(
//        TeamMemberData(
//            id = 1,
//            imageRes = R.drawable.robby,
//            nameLine1 = "Growing Future",
//            nameLine2 = "Leader",
//            role = "Manager",
//            description = "Improve well being, grow future leader, become the greatness human"
//        ),
//        TeamMemberData(
//            id = 2,
//            imageRes = R.drawable.robby,
//            nameLine1 = "Innovating Tech",
//            nameLine2 = "Solutions",
//            role = "Lead Developer",
//            description = "Crafting cutting-edge applications and mentoring junior developers."
//        ),
//        TeamMemberData(
//            id = 3,
//            imageRes = R.drawable.robby,
//            nameLine1 = "Driving Market",
//            nameLine2 = "Strategy",
//            role = "Marketing Head",
//            description = "Expanding brand reach and engaging with our global community."
//        )
//    )
//}


//// SECTION: UI COMPOSABLES
//
//@Composable
//fun TextOverlay(text: String) {
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(8.dp))
//            .background(Color.Black.copy(alpha = 0.5f))
//            .padding(horizontal = 12.dp, vertical = 6.dp)
//    ) {
//        Text(
//            text = text,
//            color = Color.White,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold
//        )
//    }
//}
//
//@Composable
//fun TeamMemberCardItem(
//    memberData: TeamMemberData,
//    orangeColor: Color,
//    darkTextColor: Color,
//    darkBlueTextColor: Color,
//    creamBackgroundColor: Color
//) {
//    Card(
//        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
//        shape = RoundedCornerShape(0.dp),
//        colors = CardDefaults.cardColors(containerColor = creamBackgroundColor),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//    ) {
//        ConstraintLayout(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            val (orangeBackgroundShape, profileImage, textOverlaysOnImage, managerTitleRow, descriptionText, findOutMoreRow) = createRefs()
//
//            Box(
//                modifier = Modifier
//                    .constrainAs(orangeBackgroundShape) {
//                        top.linkTo(parent.top, margin = 100.dp)
//                        start.linkTo(parent.start, margin = 35.dp)
//                        end.linkTo(parent.end, margin = 35.dp)
//                        width = Dimension.fillToConstraints
//                    }
//                    .fillMaxWidth()
//                    .height(220.dp)
//                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
//                    .background(orangeColor)
//            )
//
//            Image(
//                painter = painterResource(id = android.R.drawable.sym_def_app_icon), // Placeholder aman
//                // painter = painterResource(id = memberData.imageRes), // Gunakan ini jika R.drawable.robby valid
//                contentDescription = "Team Member: ${memberData.nameLine1} ${memberData.nameLine2}",
//                contentScale = ContentScale.Fit,
//                alignment = Alignment.BottomCenter,
//                modifier = Modifier
//                    .constrainAs(profileImage) {
//                        bottom.linkTo(orangeBackgroundShape.bottom)
//                        centerHorizontallyTo(orangeBackgroundShape)
//                        height = Dimension.value(320.dp)
//                        width = Dimension.percent(0.85f)
//                    }
//            )
//
//            Column(
//                modifier = Modifier
//                    .constrainAs(textOverlaysOnImage) {
//                        bottom.linkTo(profileImage.bottom, margin = 20.dp)
//                        start.linkTo(profileImage.start, margin = 20.dp)
//                    }
//            ) {
//                TextOverlay(memberData.nameLine1)
//                Spacer(modifier = Modifier.height(4.dp))
//                TextOverlay(memberData.nameLine2)
//            }
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .constrainAs(managerTitleRow) {
//                        top.linkTo(orangeBackgroundShape.bottom, margin = 20.dp)
//                        start.linkTo(parent.start, margin = 35.dp)
//                        bottom.linkTo(descriptionText.top, margin = 0.dp)
//                    }
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Star,
//                    contentDescription = "Star Icon",
//                    tint = orangeColor,
//                    modifier = Modifier.size(35.dp)
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = memberData.role,
//                    fontSize = 30.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = darkTextColor
//                )
//            }
//
//            Text(
//                text = memberData.description,
//                fontSize = 20.sp,
//                color = orangeColor,
//                lineHeight = 30.sp,
//                modifier = Modifier
//                    .constrainAs(descriptionText) {
//                        top.linkTo(managerTitleRow.bottom, margin = 10.dp)
//                        start.linkTo(parent.start, margin = 35.dp)
//                        end.linkTo(parent.end, margin = 80.dp)
//                        width = Dimension.fillToConstraints
//                        bottom.linkTo(findOutMoreRow.top, margin = 0.dp)
//                    }
//            )
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .constrainAs(findOutMoreRow) {
//                        top.linkTo(descriptionText.bottom, margin = 10.dp)
//                        start.linkTo(parent.start, margin = 35.dp)
//                        bottom.linkTo(parent.bottom, margin = 20.dp)
//                    }
//                    .clickable { /* Aksi klik detail untuk memberData.id */ }
//            ) {
//                Text(
//                    text = "Find out more",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = darkBlueTextColor
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
//                    contentDescription = "Arrow Forward",
//                    tint = darkBlueTextColor,
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//        }
//    }
//}
//
//
//@Composable
//fun TeamMemberCardScreen(teamMembers: List<TeamMemberData>) { // Terima List secara langsung
//    val orangeColor = Color(0xFFFF6F3E)
//    val creamBackgroundColor = Color(0xFFFFF9F5)
//    val darkTextColor = Color(0xFF333333)
//    val darkBlueTextColor = Color(0xFF0D47A1)
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(creamBackgroundColor)
//            .padding(16.dp)
//    ) {
//        ConstraintLayout(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            val (ourTeamTitleBox, inspiringText, memberList) = createRefs()
//
//            Box(
//                modifier = Modifier
//                    .constrainAs(ourTeamTitleBox) {
//                        top.linkTo(parent.top, margin = 20.dp)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }
//                    .background(orangeColor)
//                    .padding(horizontal = 24.dp, vertical = 8.dp)
//            ) {
//                Text(
//                    text = "OUR TEAM",
//                    color = Color.White,
//                    fontSize = 32.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Text(
//                text = "Inspiring change with motivational keynotes,\nWorkshop, and seminar for",
//                fontSize = 14.sp,
//                color = darkTextColor,
//                fontWeight = FontWeight.Medium,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .constrainAs(inspiringText) {
//                        top.linkTo(ourTeamTitleBox.bottom, margin = 12.dp)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        width = Dimension.wrapContent
//                    }
//                    .padding(horizontal = 16.dp)
//            )
//
//            LazyColumn(
//                modifier = Modifier
//                    .constrainAs(memberList) {
//                        top.linkTo(inspiringText.bottom, margin = 20.dp)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        bottom.linkTo(parent.bottom)
//                        width = Dimension.fillToConstraints
//                        height = Dimension.fillToConstraints
//                    },
//                contentPadding = PaddingValues(bottom = 16.dp)
//            ) {
//                items(teamMembers, key = { it.id }) { memberData ->
//                    TeamMemberCardItem(
//                        memberData = memberData,
//                        orangeColor = orangeColor,
//                        darkTextColor = darkTextColor,
//                        darkBlueTextColor = darkBlueTextColor,
//                        creamBackgroundColor = creamBackgroundColor
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, widthDp = 400, heightDp = 900)
//@Composable
//fun TeamMemberCardScreenPreview() {
//    val sampleMembers = getSampleTeamMembers() // Dapatkan data sampel langsung
//
//    ANTINGANGGURTheme {
//        TeamMemberCardScreen(teamMembers = sampleMembers) // Kirim data sampel
//    }
//}
