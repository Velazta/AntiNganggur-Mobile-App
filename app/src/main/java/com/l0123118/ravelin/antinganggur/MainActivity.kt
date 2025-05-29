package com.l0123118.ravelin.antinganggur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.navigation.AppNavHost
import com.l0123118.ravelin.antinganggur.navigation.AppTopBar
import com.l0123118.ravelin.antinganggur.navigation.DrawerBody
import com.l0123118.ravelin.antinganggur.navigation.DrawerHeader
import com.l0123118.ravelin.antinganggur.navigation.Screen
import com.l0123118.ravelin.antinganggur.ui.theme.ANTINGANGGURTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANTINGANGGURTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val showNavigationUI = currentRoute !in listOf(
                    Screen.Login.route,
                    Screen.SignIn.route
                )

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = showNavigationUI,
                    drawerContent = {
                        ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                            // Memanggil DrawerHeader dari Navigation.kt
                            DrawerHeader()
                            // Memanggil DrawerBody dari Navigation.kt
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
                            if (showNavigationUI) {
                                AppTopBar(
                                    scope = scope,
                                    drawerState = drawerState,
                                    navController = navController
                                )
                            }
                        },
                        content = { innerPadding -> // innerPadding dari Scaffold
                            // AppNavHost akan menangani paddingnya sendiri
                            AppNavHost(
                                navController = navController,
                                innerPadding = if (showNavigationUI) innerPadding else PaddingValues(
                                    0.dp
                                ),
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    )
                }
            }
        }
    }
}

//@Composable
//fun AntiNganggurApp(navController: NavController) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Home Screen (AntiNganggurApp Content)")
//    }
//}
//
//@Preview(showBackground = true, name = "Default Content Preview") // Nama diubah agar lebih deskriptif
//@Composable
//fun DefaultAppContentPreview() {
//    ANTINGANGGURTheme {
//        AntiNganggurApp(navController = rememberNavController())
//    }
//}