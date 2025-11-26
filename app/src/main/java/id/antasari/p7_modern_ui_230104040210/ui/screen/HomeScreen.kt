package id.antasari.p7_modern_ui_230104040210.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.antasari.p7_modern_ui_230104040210.ui.theme.CardBackgroundDark
import id.antasari.p7_modern_ui_230104040210.ui.theme.CardBackgroundLight
import id.antasari.p7_modern_ui_230104040210.ui.theme.SurfaceDark
import java.text.SimpleDateFormat
import java.util.*

// Warna Khusus
val ModernLime = Color(0xFFD0FD3E)
val SoftGrayBg = Color(0xFFF8F9FA)

@Composable
fun HomeScreen(
    navController: NavController? = null,
    isDarkTheme: Boolean
) {
    // Logika Warna Dark Mode
    val bgColor = if (isDarkTheme) SurfaceDark else SoftGrayBg
    val cardColor = if (isDarkTheme) CardBackgroundDark else CardBackgroundLight
    val textColor = if (isDarkTheme) Color.White else Color(0xFF1D1B20)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        // 1. Header (Profile & Settings + Notif)
        HomeHeaderModern(navController, textColor, isDarkTheme)

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Hero Card (Weekly Progress)
        WeeklyProgressCard(textColor)

        Spacer(modifier = Modifier.height(20.dp))

        // 3. Stats Grid
        StatsGridSection(cardColor, textColor)

        Spacer(modifier = Modifier.height(24.dp))

        // 4. Calendar Strip (DINAMIS)
        CalendarStripSection(textColor, isDarkTheme)

        Spacer(modifier = Modifier.height(24.dp))

        // 5. Meal Lists
        MealSection(cardColor, textColor)

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun HomeHeaderModern(navController: NavController?, textColor: Color, isDarkTheme: Boolean) {
    val iconTint = if (isDarkTheme) Color.White else Color.Black
    val borderColor = if (isDarkTheme) Color.Gray else Color.LightGray

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "Good morning!", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Text(text = "Muhammad Hifzi", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = textColor)
            }
        }

        Row {
            // ICON NOTIFIKASI
            IconButton(
                onClick = { /* Open Notif */ },
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, borderColor.copy(alpha = 0.5f), CircleShape)
            ) {
                Icon(Icons.Outlined.Notifications, contentDescription = null, modifier = Modifier.size(22.dp), tint = iconTint)
            }
            Spacer(modifier = Modifier.width(8.dp))

            // ICON SETTINGS
            IconButton(
                onClick = { navController?.navigate("settings") },
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, borderColor.copy(alpha = 0.5f), CircleShape)
            ) {
                Icon(Icons.Default.Settings, contentDescription = null, modifier = Modifier.size(20.dp), tint = iconTint)
            }
        }
    }
}

@Composable
fun WeeklyProgressCard(textColor: Color) {
    val cardTextColor = Color(0xFF1D1B20)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = ModernLime),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.3f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Bolt, contentDescription = null, tint = Color.Black.copy(alpha = 0.6f), modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Daily intake", style = MaterialTheme.typography.labelSmall, color = Color.Black.copy(alpha = 0.7f))
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Your Weekly\nProgress",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = cardTextColor,
                    lineHeight = 32.sp
                )
            }

            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(90.dp)) {
                    drawArc(
                        color = Color.White.copy(alpha = 0.4f),
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = 25f, cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = Color.White,
                        startAngle = -90f,
                        sweepAngle = 270f,
                        useCenter = false,
                        style = Stroke(width = 25f, cap = StrokeCap.Round)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("6", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = cardTextColor)
                    Text("days", style = MaterialTheme.typography.labelSmall, color = cardTextColor)
                }
            }
        }
    }
}

@Composable
fun StatsGridSection(cardColor: Color, textColor: Color) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        StatCard(Modifier.weight(1f), "Step to\nwalk", "5,500", "steps", Icons.Default.DirectionsWalk, Color(0xFFFF7043), Color(0xFFFBE9E7), cardColor, textColor)
        StatCard(Modifier.weight(1f), "Drink\nWater", "12", "glass", Icons.Default.WaterDrop, Color(0xFF29B6F6), Color(0xFFE1F5FE), cardColor, textColor)
    }
}

@Composable
fun StatCard(
    modifier: Modifier, title: String, value: String, unit: String, icon: ImageVector, iconColor: Color, iconBg: Color,
    cardBg: Color, textColor: Color
) {
    Card(
        modifier = modifier.height(150.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                Text(title, style = MaterialTheme.typography.bodyMedium, color = Color.Gray, lineHeight = 18.sp)
                Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(iconBg), contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(18.dp))
                }
            }
            Column {
                Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = textColor)
                Text(unit, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
        }
    }
}

// --- UPDATED: CALENDAR STRIP (LOGIKA TANGGAL HARI INI) ---
@Composable
fun CalendarStripSection(textColor: Color, isDarkTheme: Boolean) {
    // 1. Persiapan Data Tanggal
    val calendar = Calendar.getInstance()

    // Format Header: "November 2025"
    val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    val currentMonthYear = monthYearFormat.format(calendar.time)

    // Simpan tanggal hari ini untuk cek highlight
    val today = Calendar.getInstance()

    // Set kalender ke awal minggu (Misal: Minggu/Sunday) untuk membuat strip 7 hari
    // Atau kita buat strip 7 hari dimulai dari hari ini?
    // Biasanya kalender strip menampilkan Minggu-Sabtu dari minggu ini.
    calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)

    Column {
        // Header Bulan & Navigasi
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(currentMonthYear, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = textColor)
            Row {
                Icon(Icons.Default.ChevronLeft, contentDescription = null, tint = Color.Gray)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Row Hari & Tanggal
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            // Loop untuk 7 hari ke depan (dari awal minggu)
            for (i in 0..6) {
                // Format Hari: "M", "S", "S" ... (ambil huruf depannya saja)
                // EEEEEE -> "Sunday" -> take(1) -> "S"
                val dayName = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time).take(1)

                // Format Tanggal: "19", "20"
                val dateNum = SimpleDateFormat("dd", Locale.getDefault()).format(calendar.time)

                // Cek apakah tanggal ini adalah Hari Ini
                val isToday = (calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) &&
                        (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR))

                // Tentukan warna
                val bgItem = if (isToday) ModernLime else Color.Transparent
                val textDayColor = if (isToday) Color(0xFF1D1B20) else Color.Gray
                val textDateColor = if (isToday) Color(0xFF1D1B20) else textColor

                // UI Item Tanggal
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(bgItem)
                        .padding(vertical = 8.dp)
                ) {
                    Text(dayName, style = MaterialTheme.typography.labelSmall, color = textDayColor)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(dateNum, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = textDateColor)
                }

                // Maju ke hari berikutnya
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
        }
    }
}

@Composable
fun MealSection(cardColor: Color, textColor: Color) {
    Column {
        MealItem("Breakfast", "456 - 512", Color(0xFFFFB74D), cardColor, textColor)
        Spacer(modifier = Modifier.height(16.dp))
        MealItem("Lunch time", "456 - 512", Color(0xFFFFB74D), cardColor, textColor)
    }
}

@Composable
fun MealItem(title: String, kcal: String, iconColor: Color, cardBg: Color, textColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFFFFF3E0)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = iconColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = textColor)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("$kcal kcal", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                    }
                }
            }
            Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(SoftGrayBg).clickable { }, contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.Black)
            }
        }
    }
}