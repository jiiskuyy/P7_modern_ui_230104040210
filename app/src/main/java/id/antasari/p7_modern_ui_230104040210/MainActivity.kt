package id.antasari.p7_modern_ui_230104040210

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import id.antasari.p7_modern_ui_230104040210.ui.screen.LoginScreen // Import Login
import id.antasari.p7_modern_ui_230104040210.ui.theme.P7_modern_ui_230104040210Theme
import id.antasari.p7_modern_ui_230104040210.ui.theme.components.BottomBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemIsDark = isSystemInDarkTheme()
            var isDarkTheme by remember { mutableStateOf(systemIsDark) }

            // State untuk cek apakah user sudah login atau belum
            var isLoggedIn by remember { mutableStateOf(false) }

            P7_modern_ui_230104040210Theme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoggedIn) {
                        // Jika SUDAH login, tampilkan Aplikasi Utama (Bottom Nav)
                        MainScreen(
                            isDarkTheme = isDarkTheme,
                            onThemeChange = { newMode -> isDarkTheme = newMode },
                            onLogout = { isLoggedIn = false } // Fitur Logout
                        )
                    } else {
                        // Jika BELUM login, tampilkan Layar Login
                        LoginScreen(onLoginSuccess = { isLoggedIn = true })
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onLogout: () -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        // MainScreen memanggil BottomNavGraph (Benar)
        BottomNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            isDarkTheme = isDarkTheme,
            onThemeChange = onThemeChange
        )
    }
}