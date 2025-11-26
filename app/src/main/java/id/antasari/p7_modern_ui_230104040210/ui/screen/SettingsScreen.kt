package id.antasari.p7_modern_ui_230104040210.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.antasari.p7_modern_ui_230104040210.ui.components.PrimaryButton
import id.antasari.p7_modern_ui_230104040210.ui.theme.PrimaryTeal
import id.antasari.p7_modern_ui_230104040210.ui.theme.TextBlack
import id.antasari.p7_modern_ui_230104040210.ui.theme.TextGray

@Composable
fun SettingsScreen(
    navController: NavController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    // State dummy untuk toggle settings
    var notifEnabled by remember { mutableStateOf(true) }
    var biometricsEnabled by remember { mutableStateOf(false) }
    var twoFactorEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()) // Agar bisa discroll
            .padding(24.dp)
    ) {
        // 1. Header dengan Tombol Kembali
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 30.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Pengaturan",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // 2. SEKSI KEAMANAN AKUN
        SettingsSectionTitle("Keamanan & Akun")

        SettingsCard {
            // Ubah Password
            SettingsClickableItem(
                icon = Icons.Default.Lock,
                title = "Ubah Kata Sandi",
                subtitle = "Update sandi secara berkala",
                onClick = { /* Navigasi ke ubah password */ }
            )

            Divider(color = Color.LightGray.copy(alpha = 0.3f))

            // 2FA Toggle
            SettingsSwitchItem(
                icon = Icons.Default.Security,
                title = "Verifikasi 2 Langkah",
                isChecked = twoFactorEnabled,
                onCheckedChange = { twoFactorEnabled = it }
            )

            Divider(color = Color.LightGray.copy(alpha = 0.3f))

            // Biometric Toggle
            SettingsSwitchItem(
                icon = Icons.Default.Fingerprint,
                title = "Login Biometrik",
                subtitle = "Gunakan sidik jari / wajah",
                isChecked = biometricsEnabled,
                onCheckedChange = { biometricsEnabled = it }
            )

            Divider(color = Color.LightGray.copy(alpha = 0.3f))

            // Manage Devices
            SettingsClickableItem(
                icon = Icons.Default.Devices,
                title = "Perangkat Terhubung",
                subtitle = "Samsung S23, iPhone 13",
                onClick = { /* Navigasi ke device manager */ }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. SEKSI UMUM (General)
        SettingsSectionTitle("Umum & Tampilan")

        SettingsCard {
            // Dark Mode (Fitur yang sudah ada)
            SettingsSwitchItem(
                icon = Icons.Default.DarkMode,
                title = "Mode Gelap",
                isChecked = isDarkTheme,
                onCheckedChange = onThemeChange
            )

            Divider(color = Color.LightGray.copy(alpha = 0.3f))

            // Notifikasi
            SettingsSwitchItem(
                icon = Icons.Default.Notifications,
                title = "Notifikasi Push",
                isChecked = notifEnabled,
                onCheckedChange = { notifEnabled = it }
            )

            Divider(color = Color.LightGray.copy(alpha = 0.3f))

            // Bahasa
            SettingsClickableItem(
                icon = Icons.Default.Language,
                title = "Bahasa Aplikasi",
                subtitle = "Indonesia (ID)",
                onClick = { /* Dialog ganti bahasa */ }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 4. SEKSI DUKUNGAN
        SettingsSectionTitle("Dukungan & Info")

        SettingsCard {
            SettingsClickableItem(
                icon = Icons.Default.HelpOutline,
                title = "Pusat Bantuan",
                onClick = { }
            )
            Divider(color = Color.LightGray.copy(alpha = 0.3f))
            SettingsClickableItem(
                icon = Icons.Default.PrivacyTip,
                title = "Kebijakan Privasi",
                onClick = { }
            )
            Divider(color = Color.LightGray.copy(alpha = 0.3f))
            SettingsClickableItem(
                icon = Icons.Default.Info,
                title = "Versi Aplikasi",
                subtitle = "v1.0.0 (Beta)",
                showArrow = false, // Tidak perlu panah
                onClick = { }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 5. LOGOUT
        PrimaryButton(
            onClick = { /* Handle Logout Logic here */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Keluar Aplikasi", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

// --- KOMPONEN REUSABLE AGAR KODINGAN RAPI ---

@Composable
fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        color = PrimaryTeal,
        modifier = Modifier.padding(bottom = 12.dp, start = 4.dp)
    )
}

@Composable
fun SettingsCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), // Putih di light mode
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            content = content
        )
    }
}

@Composable
fun SettingsSwitchItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Icon(icon, contentDescription = null, tint = TextGray, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
                if (subtitle != null) {
                    Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = TextGray)
                }
            }
        }
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryTeal,
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color.LightGray.copy(alpha = 0.3f)
            )
        )
    }
}

@Composable
fun SettingsClickableItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    showArrow: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Icon(icon, contentDescription = null, tint = TextGray, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
                if (subtitle != null) {
                    Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = TextGray)
                }
            }
        }
        if (showArrow) {
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextGray)
        }
    }
}