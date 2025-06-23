package com.l0123118.ravelin.antinganggur.navigation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.menulist.mainpage.Home
import com.l0123118.ravelin.antinganggur.authentification.LoginPage
import com.l0123118.ravelin.antinganggur.authentification.SignInPage2
import com.l0123118.ravelin.antinganggur.menulist.contactpage.ContactPage
import com.l0123118.ravelin.antinganggur.menulist.lowonganpage.LowonganScreen
import com.l0123118.ravelin.antinganggur.R
import com.l0123118.ravelin.antinganggur.menulist.aboutuspage.AboutUsPage
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme
import com.l0123118.ravelin.antinganggur.ui.theme.AntiNganggurDarkGray
import com.l0123118.ravelin.antinganggur.ui.theme.AntiNganggurOrange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch




@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route, // Menggunakan route dari AppDestination.kt
        modifier = modifier.padding(innerPadding)
    ) {
        composable(Screen.Home.route) {

            Home(navController = navController)
        }
        composable(Screen.Login.route) {

            LoginPage(navController = navController)
        }
        composable(Screen.SignIn.route) {

            SignInPage2(navController = navController)
        }

        composable(Screen.ContactPage.route) {
            ContactPage(navController = navController)
        }

        composable(Screen.LowonganScreen.route) {
            LowonganScreen()
        }
        composable(Screen.AboutUs.route) {
            AboutUsPage()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavController

) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = AntiNganggurDarkGray, fontWeight = FontWeight.Bold)) {
                        append("Anti")
                    }
                    withStyle(style = SpanStyle(color = AntiNganggurOrange, fontWeight = FontWeight.Bold)) {
                        append("Nganggur")
                    }
                },
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoantinganggur),
                    contentDescription = "Logo AntiNganggur",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                scope.launch {
                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Buka menu navigasi"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = AntiNganggurDarkGray,
            navigationIconContentColor = Color.Unspecified,
            actionIconContentColor = AntiNganggurDarkGray
        )
    )
}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logoantinganggur), // Pastikan drawable ini ada
                contentDescription = "Logo AntiNganggur Header",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "AntiNganggur", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = AntiNganggurOrange)
        }
    }
}

@Composable
fun DrawerBody(
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
    previewSelectedRoute: String? = null
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = previewSelectedRoute ?: navBackStackEntry?.destination?.route

    Column(modifier) {
        drawerMenuItems.forEach { item ->
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (previewSelectedRoute == null) {
                        scope.launch {
                            drawerState.close()
                        }
                        if (navBackStackEntry?.destination?.route != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = AntiNganggurOrange,
                    selectedTextColor = Color.White,
                    selectedIconColor = Color.White,
                    unselectedContainerColor = Color.Transparent,
                    unselectedTextColor = AntiNganggurDarkGray,
                    unselectedIconColor = AntiNganggurDarkGray
                )

            )
        }
    }
}

// PRIPIWW

@Preview(showBackground = true)
@Composable
fun AppTopBarPreview() {
    ANTINGANGGURTheme {
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        AppTopBar(scope = scope, drawerState = drawerState, navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerHeaderPreview() {
    ANTINGANGGURTheme {
        DrawerHeader()
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun DrawerBodyPreview() {
    ANTINGANGGURTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        ModalDrawerSheet {
            DrawerBody(
                navController = navController,
                scope = scope,
                drawerState = drawerState,

                )
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun DrawerBodyItemSelectedPreview() {
    ANTINGANGGURTheme {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        ModalDrawerSheet {
            DrawerBody(
                navController = navController,
                scope = scope,
                drawerState = drawerState,

                previewSelectedRoute = Screen.Login.route
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Full App Structure - Closed Drawer")
@Composable
fun FullAppStructurePreview_DrawerClosed() {
    ANTINGANGGURTheme {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier.width(300.dp)) { // Standard width for drawer
                    DrawerHeader()
                    DrawerBody(
                        navController = navController,
                        scope = scope,
                        drawerState = drawerState,

                        )
                }
            }
        ) {
            Scaffold(
                topBar = {
                    AppTopBar(scope = scope, drawerState = drawerState, navController = rememberNavController())
                },
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Main Content Area (Simulated AppNavHost)")
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Full App Structure - Open Drawer")
@Composable
fun FullAppStructurePreview_DrawerOpen() {
    ANTINGANGGURTheme {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                    DrawerHeader()
                    DrawerBody(
                        navController = navController,
                        scope = scope,
                        drawerState = drawerState,

                        previewSelectedRoute = Screen.Home.route // Show Home as selected
                    )
                }
            },
            gesturesEnabled = true
        ) {
            Scaffold(
                topBar = {
                    AppTopBar(scope = scope, drawerState = drawerState, navController = rememberNavController())
                },
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Main Content Area")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "AppNavHost Preview (Home Screen)")
@Composable
fun AppNavHostPreview() {
    ANTINGANGGURTheme {
        val navController = rememberNavController()
        AppNavHost(navController = navController, innerPadding = PaddingValues(0.dp))
    }
}