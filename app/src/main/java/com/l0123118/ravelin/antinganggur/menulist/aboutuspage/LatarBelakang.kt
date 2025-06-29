package com.l0123118.ravelin.antinganggur.menulist.aboutuspage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.OrangePrimary

@Composable
fun LatarBelakang() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .drawBehind {
                    val path = Path().apply {
                        moveTo(0f, 0f)
                        lineTo(size.width, 0f)
                        lineTo(size.width, size.height * 0.80f)
                        quadraticBezierTo(
                            x1 = size.width / 2f,
                            y1 = size.height * 1.15f,
                            x2 = 0f,
                            y2 = size.height * 0.80f
                        )
                        close()
                    }
                    drawPath(
                        path = path,
                        color = OrangePrimary
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 40.dp, bottom = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Selamat Datang Di Perusahaan Kami",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    lineHeight = 38.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "AntiNganggur didirikan pada tahun 2020 oleh sekelompok profesional IT yang melihat adanya kesenjangan antara kebutuhan industri dan keterampilan lulusan teknologi di Indonesia.",
                    color = Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Berawal dari komunitas kecil yang berbagi pengetahuan secara online, kami berkembang menjadi platform komprehensif yang menghubungkan talenta IT dengan peluang karir dan pembelajaran yang relevan dengan kebutuhan industri.",
                    color = Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Saat ini, AntiNganggur telah membantu lebih dari 5.000 profesional IT menemukan pekerjaan impian mereka dan membekali lebih dari 15.000 pembelajar dengan keterampilan digital yang dibutuhkan di era modern.",
                    color = Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ilustrationaboutpage),
                contentDescription = "Ilustrasi Perusahaan",
                contentScale = ContentScale.Crop,
                alignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true, name = "Latar Belakang Preview")
@Composable
fun LatarBelakangPreview() {
    ANTINGANGGURTheme {
        LatarBelakang()
    }
}