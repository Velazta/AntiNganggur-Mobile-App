import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension


data class TeamMember(
    val id: Int,
    @DrawableRes val imageResId: Int,
    val overlayTexts: List<String>,
    val title: String,
    val description: String
)

@Composable
fun TextOverlay(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black.copy(alpha = 0.5f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Composable
fun TeamMemberCardItem(
    member: TeamMember,
    orangeColor: Color,
    darkBlueTextColor: Color,
    creamBackgroundColor: Color,
    darkTextColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = creamBackgroundColor),
        modifier = modifier
            .padding(bottom = 16.dp)

    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (orangeBackgroundShape, profileImage, textOverlaysOnImage, managerTitleRow, descriptionText, findOutMoreRow) = createRefs()

            Box(
                modifier = Modifier
                    .constrainAs(orangeBackgroundShape) {
                        top.linkTo(parent.top, margin = 120.dp)
                        start.linkTo(parent.start, margin = 50.dp)
                        end.linkTo(parent.end, margin = 50.dp)
                        width = Dimension.fillToConstraints
                    }
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(orangeColor)
            )

            Image(
                painter = painterResource(id = member.imageResId),
                contentDescription = "${member.title} - ${member.overlayTexts.joinToString(" ")}",
                contentScale = ContentScale.Fit,
                alignment = Alignment.BottomCenter,
                modifier = Modifier
                    .constrainAs(profileImage) {
                        bottom.linkTo(orangeBackgroundShape.bottom)
                        centerHorizontallyTo(orangeBackgroundShape)
                        height = Dimension.value(320.dp)
                        width = Dimension.percent(0.85f)
                    }
            )

            Column(
                modifier = Modifier
                    .constrainAs(textOverlaysOnImage) {
                        bottom.linkTo(profileImage.bottom, margin = 20.dp)
                        start.linkTo(profileImage.start, margin = 40.dp)
                    }
            ) {
                member.overlayTexts.forEach { text ->
                    TextOverlay(text)
                    if (member.overlayTexts.last() != text) {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .constrainAs(managerTitleRow) {
                        top.linkTo(orangeBackgroundShape.bottom, margin = 20.dp)
                        start.linkTo(parent.start, margin = 43.dp)
                        end.linkTo(parent.end, margin = 43.dp)
                        width = Dimension.fillToConstraints
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star Icon",
                    tint = orangeColor,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = member.title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkTextColor,
                    textAlign = TextAlign.Start
                )
            }

            Text(
                text = member.description,
                fontSize = 20.sp,
                color = orangeColor,
                textAlign = TextAlign.Justify,
                lineHeight = 30.sp,
                modifier = Modifier
                    .constrainAs(descriptionText) {
                        top.linkTo(managerTitleRow.bottom, margin = 10.dp)
                        start.linkTo(parent.start, margin = 50.dp)
                        end.linkTo(parent.end, margin = 50.dp)
                        width = Dimension.fillToConstraints
                    }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .constrainAs(findOutMoreRow) {
                        top.linkTo(descriptionText.bottom, margin = 10.dp)
                        start.linkTo(parent.start, margin = 50.dp)
                        bottom.linkTo(parent.bottom, margin = 20.dp)
                    }
                    .clickable { /* TODO: Handle click */ }
            ) {
                Text(
                    text = "Find out more",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkBlueTextColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Arrow Forward",
                    tint = darkBlueTextColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun TeamHeaderSection(
    orangeColor: Color,
    darkTextColor: Color,
    creamBackgroundColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(creamBackgroundColor)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp) // Sesuaikan padding
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (ourTeamTitleBox, inspiringText) = createRefs()

            Box(
                modifier = Modifier
                    .constrainAs(ourTeamTitleBox) {
                        top.linkTo(parent.top, margin = 20.dp)
                        centerHorizontallyTo(parent)
                    }
                    .background(orangeColor)
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "OUR TEAM",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Inspiring change with motivational keynotes,\nWorkshop, and seminar for",
                fontSize = 14.sp,
                color = darkTextColor,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(inspiringText) {
                        top.linkTo(ourTeamTitleBox.bottom, margin = 12.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.wrapContent
                    }
                    .padding(horizontal = 16.dp)
            )
        }
    }
}


@Composable
fun TeamMembersRowSection(
    members: List<TeamMember>,
    orangeColor: Color,
    darkBlueTextColor: Color,
    creamBackgroundColor: Color,
    darkTextColor: Color,
    cardWidth: Dp = 320.dp,
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val calculatedStartPadding = (screenWidth - cardWidth) / 2
    val startPadding = max(16.dp, calculatedStartPadding)

    val endPadding = 16.dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(creamBackgroundColor)
            .padding(vertical = 8.dp)
    ) {

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = startPadding, end = endPadding),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            items(members, key = { member -> member.id }) { member ->
                TeamMemberCardItem(
                    member = member,
                    orangeColor = orangeColor,
                    darkBlueTextColor = darkBlueTextColor,
                    creamBackgroundColor = creamBackgroundColor,
                    darkTextColor = darkTextColor,
                    modifier = Modifier
                        .width(cardWidth)
                        .padding(bottom = 0.dp)
                )
            }
        }
    }
}


//import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
//import com.l0123118.ravelin.antinganggur.ui.theme.LightOrange

//package com.l0123118.ravelin.antinganggur.menulist.aboutuspage
//
//import androidx.annotation.DrawableRes
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
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
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
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.constraintlayout.compose.Dimension
//import com.l0123118.ravelin.antinganggur.R
//import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
//import com.l0123118.ravelin.antinganggur.ui.theme.LightOrange
//
//
//////SECTION FOR TEAM MEMBER
////
////// Data class to represent a team member
////data class TeamMember(
////    val id: Int, // Unique ID for LazyColumn key
////    @DrawableRes val imageResId: Int,
////    val overlayTexts: List<String>,
////    val title: String,
////    val description: String
////)
////
////// Original TextOverlay composable (unchanged)
////@Composable
////fun TextOverlay(text: String) {
////    Box(
////        modifier = Modifier
////            .clip(RoundedCornerShape(8.dp)) // Standard Jetpack Compose syntax
////            .background(Color.Black.copy(alpha = 0.5f))
////            .padding(horizontal = 12.dp, vertical = 6.dp)
////    ) {
////        Text(
////            text = text,
////            color = Color.White,
////            fontSize = 18.sp, // Standard Jetpack Compose syntax
////            fontWeight = FontWeight.SemiBold
////        )
////    }
////}
////
////
////// New composable for a single Team Member Card
////@Composable
////fun TeamMemberCardItem(
////    member: TeamMember,
////    orangeColor: Color,
////    darkBlueTextColor: Color,
////    creamBackgroundColor: Color,
////    darkTextColor: Color
////) {
////    Card(
////        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // Standard Jetpack Compose syntax
////        shape = RoundedCornerShape(0.dp), // Standard Jetpack Compose syntax
////        colors = CardDefaults.cardColors(containerColor = creamBackgroundColor),
////        modifier = Modifier
////            .fillMaxWidth()
////            .padding(bottom = 16.dp) // Standard Jetpack Compose syntax
////    ) {
////        ConstraintLayout(
////            modifier = Modifier.fillMaxWidth()
////        ) {
////            val (orangeBackgroundShape, profileImage, textOverlaysOnImage, managerTitleRow, descriptionText, findOutMoreRow) = createRefs()
////
////            Box(
////                modifier = Modifier
////                    .constrainAs(orangeBackgroundShape) {
////                        top.linkTo(parent.top, margin = 120.dp) // Standard Jetpack Compose syntax
////                        start.linkTo(parent.start, margin = 50.dp) // Standard Jetpack Compose syntax
////                        end.linkTo(parent.end, margin = 50.dp) // Standard Jetpack Compose syntax
////                        width = Dimension.fillToConstraints
////                    }
////                    .fillMaxWidth()
////                    .height(220.dp) // Standard Jetpack Compose syntax
////                    .clip(RoundedCornerShape(20.dp)) // Standard Jetpack Compose syntax
////                    .background(orangeColor)
////            )
////
////            Image(
////                // In a real app, ensure member.imageResId points to a valid drawable resource
////                painter = painterResource(id = member.imageResId),
////                contentDescription = "${member.title} - ${member.overlayTexts.joinToString(" ")}",
////                contentScale = ContentScale.Fit,
////                alignment = Alignment.BottomCenter,
////                modifier = Modifier
////                    .constrainAs(profileImage) {
////                        bottom.linkTo(orangeBackgroundShape.bottom)
////                        centerHorizontallyTo(orangeBackgroundShape)
////                        height = Dimension.value(320.dp) // Standard Jetpack Compose syntax
////                        width = Dimension.percent(0.85f)
////                    }
////            )
////
////            Column(
////                modifier = Modifier
////                    .constrainAs(textOverlaysOnImage) {
////                        bottom.linkTo(profileImage.bottom, margin = 20.dp) // Standard Jetpack Compose syntax
////                        start.linkTo(profileImage.start, margin = 40.dp) // Standard Jetpack Compose syntax
////                    }
////            ) {
////                member.overlayTexts.forEach { text ->
////                    TextOverlay(text)
////                    if (member.overlayTexts.last() != text) {
////                        Spacer(modifier = Modifier.height(4.dp)) // Standard Jetpack Compose syntax
////                    }
////                }
////            }
////
////            Row(
////                verticalAlignment = Alignment.CenterVertically,
////                modifier = Modifier
////                    .constrainAs(managerTitleRow) {
////                        top.linkTo(orangeBackgroundShape.bottom, margin = 20.dp) // Standard Jetpack Compose syntax
////                        start.linkTo(parent.start, margin = 43.dp) // Standard Jetpack Compose syntax
////                    }
////            ) {
////                Icon(
////                    imageVector = Icons.Filled.Star,
////                    contentDescription = "Star Icon",
////                    tint = orangeColor,
////                    modifier = Modifier.size(50.dp) // Standard Jetpack Compose syntax
////                )
////                Spacer(modifier = Modifier.width(8.dp)) // Standard Jetpack Compose syntax
////                Text(
////                    text = member.title,
////                    fontSize = 30.sp, // Standard Jetpack Compose syntax
////                    fontWeight = FontWeight.Bold,
////                    color = darkTextColor
////                )
////            }
////
////            Text(
////                text = member.description,
////                fontSize = 20.sp, // Standard Jetpack Compose syntax
////                color = orangeColor,
////                textAlign = TextAlign.Justify,
////                lineHeight = 30.sp, // Standard Jetpack Compose syntax
////                modifier = Modifier
////                    .constrainAs(descriptionText) {
////                        top.linkTo(managerTitleRow.bottom, margin = 10.dp) // Standard Jetpack Compose syntax
////                        start.linkTo(parent.start, margin = 50.dp) // Standard Jetpack Compose syntax
////                        end.linkTo(parent.end, margin = 50.dp) // Standard Jetpack Compose syntax
////                        width = Dimension.fillToConstraints
////                    }
////            )
////
////            Row(
////                verticalAlignment = Alignment.CenterVertically,
////                modifier = Modifier
////                    .constrainAs(findOutMoreRow) {
////                        top.linkTo(descriptionText.bottom, margin = 10.dp) // Standard Jetpack Compose syntax
////                        start.linkTo(parent.start, margin = 50.dp) // Standard Jetpack Compose syntax
////                        bottom.linkTo(parent.bottom, margin = 20.dp) // Standard Jetpack Compose syntax
////                    }
////                    .clickable { /* TODO: Handle click for member.id or member object */ }
////            ) {
////                Text(
////                    text = "Find out more",
////                    fontSize = 18.sp, // Standard Jetpack Compose syntax
////                    fontWeight = FontWeight.Bold,
////                    color = darkBlueTextColor
////                )
////                Spacer(modifier = Modifier.width(8.dp)) // Standard Jetpack Compose syntax
////                Icon(
////                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
////                    contentDescription = "Arrow Forward",
////                    tint = darkBlueTextColor,
////                    modifier = Modifier.size(24.dp) // Standard Jetpack Compose syntax
////                )
////            }
////        }
////    }
////}
////
////@Preview(showBackground = true)
////@Composable
////fun TeamMemberCardScreen() {
////    val orangeColor = Color(0xFFFF6F3E)
////    val darkBlueTextColor = Color(0xFF0D47A1)
////    val creamBackgroundColor = Color(0xFFFFF9F5)
////    val darkTextColor = Color(0xFF333333)
////
////
////    val members = listOf(
////        TeamMember(
////            id = 1,
////            imageResId = R.drawable.robby, // e.g., R.drawable.robby,
////            overlayTexts = listOf("Growing Future", "Leader"),
////            title = "Manager",
////            description = "Improve well being, grow future leader, become the greatness human"
////        ),
////        TeamMember(
////            id = 2,
////            imageResId = R.drawable.robby, // e.g., R.drawable.another_person_image,
////            overlayTexts = listOf("Tech Visionary", "Architect"),
////            title = "Chief Architect",
////            description = "Designing scalable systems and future-proof technologies."
////        ),
////        TeamMember(
////            id = 3,
////            imageResId = R.drawable.robby, // e.g., R.drawable.yet_another_image,
////            overlayTexts = listOf("People Champion", "HR"),
////            title = "HR Head",
////            description = "Fostering a vibrant culture and empowering our team members."
////        )
////    )
////
////    Box(
////        modifier = Modifier
////            .fillMaxSize()
////            .background(creamBackgroundColor)
////            .padding(16.dp) // Standard Jetpack Compose syntax
////    ) {
////        ConstraintLayout(
////            modifier = Modifier.fillMaxSize()
////        ) {
////            val (ourTeamTitleBox, inspiringText, membersList) = createRefs()
////
////            Box(
////                modifier = Modifier
////                    .constrainAs(ourTeamTitleBox) {
////                        top.linkTo(parent.top, margin = 20.dp) // Standard Jetpack Compose syntax
////                        start.linkTo(parent.start)
////                        end.linkTo(parent.end)
////                    }
////                    .background(orangeColor)
////                    .padding(horizontal = 24.dp, vertical = 8.dp) // Standard Jetpack Compose syntax
////            ) {
////                Text(
////                    text = "OUR TEAM",
////                    color = Color.White,
////                    fontSize = 32.sp, // Standard Jetpack Compose syntax
////                    fontWeight = FontWeight.Bold
////                )
////            }
////
////            Text(
////                text = "Inspiring change with motivational keynotes,\nWorkshop, and seminar for",
////                fontSize = 14.sp, // Standard Jetpack Compose syntax
////                color = darkTextColor,
////                fontWeight = FontWeight.Medium,
////                textAlign = TextAlign.Center,
////                modifier = Modifier
////                    .constrainAs(inspiringText) {
////                        top.linkTo(ourTeamTitleBox.bottom, margin = 12.dp) // Standard Jetpack Compose syntax
////                        start.linkTo(parent.start)
////                        end.linkTo(parent.end)
////                        width = Dimension.wrapContent
////                    }
////                    .padding(horizontal = 16.dp) // Standard Jetpack Compose syntax
////            )
////
////            LazyColumn(
////                modifier = Modifier
////                    .constrainAs(membersList) {
////                        top.linkTo(inspiringText.bottom, margin = 20.dp) // Standard Jetpack Compose syntax
////                        start.linkTo(parent.start)
////                        end.linkTo(parent.end)
////                        bottom.linkTo(parent.bottom)
////                        height = Dimension.fillToConstraints
////                        width = Dimension.fillToConstraints
////                    }
////            ) {
////                items(members, key = { member -> member.id }) { member ->
////                    TeamMemberCardItem(
////                        member = member,
////                        orangeColor = orangeColor,
////                        darkBlueTextColor = darkBlueTextColor,
////                        creamBackgroundColor = creamBackgroundColor,
////                        darkTextColor = darkTextColor
////                    )
////                }
////            }
////        }
////    }
////}
//
//
//
//
//



