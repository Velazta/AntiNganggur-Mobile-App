package com.l0123118.ravelin.antinganggur.menulist.aboutuspage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme

val VisMisOrangeBanner = Color(0xFFF9794D)
val VisMisCardBorderColor = VisMisOrangeBanner
val VisMisCardContentBgColor = Color.White
val VisMisSectionBgColor = Color(0xFFFFFBF9)
val VisMisSubtitleTextColor = Color(0xFF5A5A5A)
val VisMisIconBgColor = Color(0xFFFFEAE4)
val VisMisIconTintColor = VisMisOrangeBanner
val VisMisTitleTextColor = VisMisOrangeBanner
val VisMisVisionTextColor = Color(0xFF8C5B4C)
val VisMisMissionTextColor = Color(0xFF4A4A4A)

@Composable
fun VisiMisiSectionNew() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(VisMisSectionBgColor)
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Banner Judul "VISI MISI"
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .background(VisMisOrangeBanner)
                .padding(horizontal = 32.dp, vertical = 8.dp)
        ) {
            Text(
                text = "VISI MISI",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Teks Subtitle
        Text(
            text = "Kami berkomitmen untuk menciptakan dampak\npositif dalam ekosistem teknologi Indonesia",
            color = VisMisSubtitleTextColor,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // LazyRow untuk Kartu Visi dan Misi
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            val uniformCardHeight = 370.dp

            item {
                VisionCardNew(
                    modifier = Modifier
                        .width(320.dp)
                        .height(uniformCardHeight)
                )
            }
            item {
                MissionCardNew(
                    modifier = Modifier
                        .width(320.dp)
                        .height(uniformCardHeight)
                )
            }
        }
    }
}

@Composable
fun CardShell(
    modifier: Modifier = Modifier,
    borderColor: Color = VisMisCardBorderColor,
    contentBackgroundColor: Color = VisMisCardContentBgColor,
    cornerRadius: Dp = 20.dp,
    borderWidth: Dp = 1.5.dp,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = RoundedCornerShape(cornerRadius), clip = false)
            .border(BorderStroke(borderWidth, borderColor), RoundedCornerShape(cornerRadius))
            .background(contentBackgroundColor, RoundedCornerShape(cornerRadius))
            .clip(RoundedCornerShape(cornerRadius))
    ) {
        content()
    }
}

@Composable
fun VisionCardNew(modifier: Modifier = Modifier) {
    CardShell(modifier = modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            val (iconContainer, title, visionText) = createRefs()

            // Area Ikon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(VisMisIconBgColor)
                    .constrainAs(iconContainer) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Lightbulb,
                    contentDescription = "Visi Icon",
                    tint = VisMisIconTintColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Teks Judul "VISI"
            Text(
                text = "VISI",
                color = VisMisTitleTextColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(iconContainer.top)
                    bottom.linkTo(iconContainer.bottom)
                    start.linkTo(iconContainer.end, margin = 12.dp)
                }
            )

            // Teks Isi Visi
            Text(
                text = "Menjadi katalisator utama dalam menciptakan ekosistem teknologi Indonesia yang inklusif, inovatif, dan berkelanjutan, di mana setiap talenta digital dapat berkembang dan berkontribusi pada kemajuan bangsa.",
                color = VisMisVisionTextColor,
                fontSize = 15.sp,
                lineHeight = 23.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.constrainAs(visionText) {
                    top.linkTo(iconContainer.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, goneMargin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.preferredWrapContent
                }
            )
        }
    }
}

@Composable
fun MissionCardNew(modifier: Modifier = Modifier) {
    val missionItems = listOf(
        "Menyediakan platform pembelajaran yang relevan dengan kebutuhan industri teknologi terkini",
        "Menyediakan platform pembelajaran yang relevan dengan kebutuhan industri teknologi terkini",
        "Menyediakan platform pembelajaran yang relevan dengan kebutuhan industri teknologi terkini"
    )

    CardShell(modifier = modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            val (iconContainer, title, missionList) = createRefs()

            // Area Ikon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(VisMisIconBgColor)
                    .constrainAs(iconContainer) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Bolt,
                    contentDescription = "Misi Icon",
                    tint = VisMisIconTintColor,
                    modifier = Modifier.size(32.dp)
                )
            }

            // Teks Judul "MISI"
            Text(
                text = "MISI",
                color = VisMisTitleTextColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(iconContainer.top)
                    bottom.linkTo(iconContainer.bottom)
                    start.linkTo(iconContainer.end, margin = 12.dp)
                }
            )

            // Daftar Poin Misi
            Column(
                modifier = Modifier.constrainAs(missionList) {
                    top.linkTo(iconContainer.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, goneMargin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.preferredWrapContent
                }
            ) {
                missionItems.forEach { missionText ->
                    MissionListItem(text = missionText)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun MissionListItem(text: String, icon: ImageVector = Icons.Filled.ChevronRight) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(
            imageVector = icon,
            contentDescription = "Mission bullet point",
            tint = VisMisIconTintColor,
            modifier = Modifier.size(20.dp).padding(top = 3.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            color = VisMisMissionTextColor,
            fontSize = 15.sp,
            lineHeight = 23.sp
        )
    }
}


// Previews
@Preview(showBackground = true, backgroundColor = 0xFFFFFBF9)
@Composable
fun VisiMisiSectionNewPreview() {
    MaterialTheme {
        VisiMisiSectionNew()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFBF9, widthDp = 350, heightDp = 320)
@Composable
fun VisionCardNewPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            VisionCardNew(modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFBF9, widthDp = 350, heightDp = 370)
@Composable
fun MissionCardNewPreview() {
    ANTINGANGGURTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MissionCardNew(modifier = Modifier.fillMaxSize())
        }
    }
}