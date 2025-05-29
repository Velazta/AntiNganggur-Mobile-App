package com.l0123118.ravelin.antinganggur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.l0123118.ravelin.antinganggur.navigation.AppNavHost
import com.l0123118.ravelin.antinganggur.navigation.AppTopBar
import com.l0123118.ravelin.antinganggur.navigation.DrawerBody
import com.l0123118.ravelin.antinganggur.navigation.DrawerHeader
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

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                            // Memanggil DrawerHeader dari Navigation.kt
                            DrawerHeader()
                            Divider()
                            // Memanggil DrawerBody dari Navigation.kt
                            DrawerBody(
                                navController = navController,
                                scope = scope,
                                drawerState = drawerState,
                                context = LocalContext.current
                            )
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            // Memanggil AppTopBar dari Navigation.kt
                            AppTopBar(
                                scope = scope,
                                drawerState = drawerState
                            )
                        }
                    ) { innerPadding ->
                        // Memanggil AppNavHost dari Navigation.kt
                        AppNavHost(
                            navController = navController,
                            innerPadding = innerPadding
                        )
                    }
                }
            }
        }
    }
}

// Composable AntiNganggurApp ini digunakan sebagai konten untuk Screen.Home
// di AppNavHost (Navigation.kt). Pastikan ini konsisten.
@Composable
fun AntiNganggurApp(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen (AntiNganggurApp Content)")
    }
}

@Preview(showBackground = true, name = "Default Content Preview") // Nama diubah agar lebih deskriptif
@Composable
fun DefaultAppContentPreview() {
    ANTINGANGGURTheme {
        AntiNganggurApp(navController = rememberNavController())
    }
}
