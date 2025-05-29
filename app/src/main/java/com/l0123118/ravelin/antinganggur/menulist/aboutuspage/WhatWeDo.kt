package com.l0123118.ravelin.antinganggur.menulist.aboutuspage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
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
fun WhatWeDo() {
    val imageGradientColors = listOf(Color(0xFFFFF6F4), Color(0xFFFFF6F4))
    val descriptionBackgroundColor = Color(0xFFFFF6F4)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(descriptionBackgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = imageGradientColors,
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.whatwedobanner),
                contentDescription = "What We Do Illustration",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 50.dp)
                    .aspectRatio(4f / 3f)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-28).dp)
                    .padding(horizontal = 20.dp)
            ) {
                WhatWeDoBannerComposable(
                    backgroundColor = OrangePrimary,
                    text = "What We Do"
                )
            }

            Text(
                text = "AntiNganggur.id didirikan untuk menjadi solusi digital dalam mengatasi permasalahan pengangguran di sektor IT. Kami menyediakan platform yang menghubungkan perusahaan dengan kandidat berkualitas, menawarkan informasi yang akurat dan relevan tentang lowongan pekerjaan serta profil perusahaan. Dengan pendekatan yang proaktif, kami berkomitmen untuk memberdayakan pencari kerja dan membantu mereka menemukan peluang karir yang tepat. Hingga saat ini, AntiNganggur.id telah menjembatani ribuan profesional IT dengan peluang kerja yang sesuai, berkontribusi pada pertumbuhan industri teknologi informasi di Indonesia.",
                color = Color.Black,
                fontSize = 15.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
            )
        }
    }
}

@Composable
fun WhatWeDoBannerComposable(backgroundColor: Color, text: String) {
    val bannerHeight = 48.dp
    val pointExtension = bannerHeight * 0.4f
    val textHorizontalPadding = 20.dp

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .height(bannerHeight)
            .wrapContentWidth()
            .drawBehind {
                val rectPartWidth = size.width - pointExtension.toPx()
                val path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(rectPartWidth, 0f)
                    lineTo(size.width, size.height / 2f)
                    lineTo(rectPartWidth, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                drawPath(path, color = backgroundColor)
            }
            .padding(start = textHorizontalPadding, end = textHorizontalPadding + pointExtension)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, name = "What We Do Section Preview")
@Composable
fun WhatWeDoSectionPreview() {
    ANTINGANGGURTheme {
        WhatWeDo()
    }
}