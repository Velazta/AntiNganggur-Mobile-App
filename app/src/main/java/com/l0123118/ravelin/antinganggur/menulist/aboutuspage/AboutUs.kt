package com.l0123118.ravelin.antinganggur.menulist.aboutuspage

import TeamHeaderSection
import TeamMember
import TeamMembersRowSection
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.LightOrange


class AboutUs : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANTINGANGGURTheme {
                AboutUsPage()
            }
        }
    }
}

@Composable
fun AboutUsPage(capabilitiesViewModel: CapabilitiesViewModel = viewModel()) {
    val orangeColor = Color(0xFFFF6F3E)
    val darkTextColorForTitle = Color(0xFF333333)
    val lightPinkBackground = Color(0xFFFFF9F7)
    val descriptionItemColor = Color(0xFF555555)
    val darkBlueTextColor = Color(0xFF0D47A1)
    val creamBackgroundColor = Color(0xFFFFF9F5)

    val capabilities by capabilitiesViewModel.capabilities.collectAsState()

    val members = listOf(
        TeamMember(1, R.drawable.robby, listOf("Ravelin Lutfhan"), "Manager", "Improve well being, grow future leader, become the greatness human"),
        TeamMember(2, R.drawable.robby, listOf("Rizky Amalia"), "Chief Architect", "Designing scalable systems and future-proof technologies."),
        TeamMember(3, R.drawable.robby, listOf("Rafi Amirudin"), "HR Head", "Fostering a vibrant culture and empowering our team members."),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightOrange),
    ) {

        item {
            LatarBelakang()
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            WhatWeDo()
        }

        item {
            VisiMisiSectionNew()
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            CapabilitiesImageSection()
        }

        item {
            Column(modifier = Modifier.fillMaxWidth().background(lightPinkBackground)) {
                CapabilitiesTitleContent(orangeColor = orangeColor, darkTextColor = darkTextColorForTitle)

                Spacer(modifier = Modifier.height(8.dp))

                capabilities.forEachIndexed { index, capability ->
                    Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                        CapabilityRowItem(
                            icon = capability.icon,
                            title = capability.title,
                            description = capability.description,
                            iconBackgroundColor = orangeColor,
                            titleColor = orangeColor,
                            descriptionColor = descriptionItemColor
                        )
                    }
                    if (index < capabilities.size - 1) {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }

                if (capabilities.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

         item {
             TeamHeaderSection(
                 orangeColor = orangeColor,
                 darkTextColor = darkTextColorForTitle,
                 creamBackgroundColor = creamBackgroundColor
             )
         }
         item {
             TeamMembersRowSection(
                 members = members,
                 orangeColor = orangeColor,
                 darkBlueTextColor = darkBlueTextColor,
                 creamBackgroundColor = creamBackgroundColor,
                 darkTextColor = darkTextColorForTitle
             )
         }
    }
}

@Preview(showBackground = true, name = "About Us Page Preview")
@Composable
fun AboutUsPagePreview() {
    ANTINGANGGURTheme {
        AboutUsPage()
    }
}

