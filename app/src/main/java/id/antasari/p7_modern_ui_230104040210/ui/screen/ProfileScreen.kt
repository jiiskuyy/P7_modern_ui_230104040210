package id.antasari.p7_modern_ui_230104040210.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import id.antasari.p7_modern_ui_230104040210.ui.components.AppTextField
import id.antasari.p7_modern_ui_230104040210.ui.theme.PrimaryTeal
import id.antasari.p7_modern_ui_230104040210.ui.theme.SurfaceDark
import id.antasari.p7_modern_ui_230104040210.ui.theme.TextBlack
import id.antasari.p7_modern_ui_230104040210.ui.theme.TextWhite

@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit,
    isDarkTheme: Boolean
) {
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("Muhammad Hifzi") }
    var username by remember { mutableStateOf("@hfziii_") }
    var email by remember { mutableStateOf("antasari@student.ac.id") }
    var phone by remember { mutableStateOf("081234567890") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri -> if (uri != null) imageUri = uri }

    // Warna Adaptif
    val bgColor = if (isDarkTheme) SurfaceDark else Color.White
    val textColor = if (isDarkTheme) TextWhite else TextBlack
    val subTextColor = if (isDarkTheme) Color.LightGray else Color.Gray

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .verticalScroll(rememberScrollState())
    ) {
        if (isEditing) {
            EditProfileContent(
                name, { name = it }, username, { username = it }, email, { email = it }, phone, { phone = it },
                imageUri, { galleryLauncher.launch("image/*") }, { isEditing = false }, { isEditing = false },
                textColor
            )
        } else {
            ViewProfileContent(
                name, username, imageUri, { isEditing = true }, onSettingsClick,
                textColor, subTextColor
            )
        }
    }
}

@Composable
fun ViewProfileContent(
    name: String, username: String, imageUri: Uri?,
    onEditClick: () -> Unit, onSettingsClick: () -> Unit,
    textColor: Color, subTextColor: Color
) {
    Column(modifier = Modifier.padding(24.dp)) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(24.dp))
            Text("Profil Saya", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = textColor)
            IconButton(onClick = onSettingsClick) {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = textColor)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Info User
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(80.dp).clip(CircleShape).background(Color.LightGray), contentAlignment = Alignment.Center) {
                if (imageUri != null) {
                    Image(rememberAsyncImagePainter(imageUri), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                } else {
                    Icon(Icons.Default.Person, null, tint = Color.White, modifier = Modifier.size(50.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = textColor)
                Text(username, style = MaterialTheme.typography.bodyMedium, color = subTextColor)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Level 5 - Health Explorer", style = MaterialTheme.typography.labelSmall, color = PrimaryTeal, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onEditClick, colors = ButtonDefaults.buttonColors(containerColor = PrimaryTeal), shape = RoundedCornerShape(8.dp), modifier = Modifier.height(40.dp)) {
            Text("Edit Data Diri", color = Color.White, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Menu Kesehatan
        SectionTitle("Data Kesehatan", textColor)
        ProfileMenuItem(Icons.Default.History, "Riwayat Latihan", textColor)
        ProfileMenuItem(Icons.Default.Flag, "Target Saya", textColor)
        ProfileMenuItem(Icons.Default.EmojiEvents, "Pencapaian & Lencana", textColor)

        Spacer(modifier = Modifier.height(20.dp))

        SectionTitle("Perangkat & Akun", textColor)
        ProfileMenuItem(Icons.Default.Watch, "Hubungkan Smartwatch", textColor)
        ProfileMenuItem(Icons.Default.HealthAndSafety, "Data Medis (Darurat)", textColor)
        ProfileMenuItem(Icons.Default.Share, "Bagikan Kemajuan", textColor)
    }
}

@Composable
fun EditProfileContent(
    name: String, onNameChange: (String) -> Unit,
    username: String, onUsernameChange: (String) -> Unit,
    email: String, onEmailChange: (String) -> Unit,
    phone: String, onPhoneChange: (String) -> Unit,
    imageUri: Uri?, onImageClick: () -> Unit,
    onBack: () -> Unit, onSave: () -> Unit,
    textColor: Color
) {
    Column(modifier = Modifier.padding(24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back", tint = textColor) }
            Text("Edit Profil", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = textColor)
            IconButton(onClick = onSave) { Icon(Icons.Default.Check, "Save", tint = PrimaryTeal) }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally).size(100.dp).clip(CircleShape).background(Color.LightGray).clickable { onImageClick() },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) Image(rememberAsyncImagePainter(imageUri), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            else Icon(Icons.Default.Person, null, tint = Color.White, modifier = Modifier.size(60.dp))
            Box(modifier = Modifier.align(Alignment.BottomEnd).size(30.dp).clip(CircleShape).background(Color.White).padding(6.dp)) {
                Icon(Icons.Default.CameraAlt, null, tint = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        EditProfileField("Nama Lengkap", name, onNameChange, textColor)
        EditProfileField("Username", username, onUsernameChange, textColor)
        EditProfileField("Email", email, onEmailChange, textColor)
        EditProfileField("Nomor HP", phone, onPhoneChange, textColor)
    }
}

@Composable
fun EditProfileField(label: String, value: String, onValueChange: (String) -> Unit, textColor: Color) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(label, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = textColor)
        Spacer(modifier = Modifier.height(8.dp))
        AppTextField(value = value, onValueChange = onValueChange, placeholder = label, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, text: String, textColor: Color) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium, color = textColor)
        }
        Icon(Icons.Default.ChevronRight, null, tint = Color.Gray)
    }
}

@Composable
fun SectionTitle(title: String, textColor: Color) {
    Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = textColor, modifier = Modifier.padding(bottom = 10.dp))
}