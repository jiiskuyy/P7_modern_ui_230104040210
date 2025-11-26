package id.antasari.p7_modern_ui_230104040210.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.antasari.p7_modern_ui_230104040210.ui.theme.CardBackgroundDark
import id.antasari.p7_modern_ui_230104040210.ui.theme.CardBackgroundLight
import id.antasari.p7_modern_ui_230104040210.ui.theme.PrimaryTeal
import id.antasari.p7_modern_ui_230104040210.ui.theme.SurfaceDark
import java.text.SimpleDateFormat
import java.util.*

data class ActivityItem(val title: String, val value: String, val target: String, val icon: ImageVector)

@Composable
fun ActivityScreen(isDarkTheme: Boolean) {
    // Logika Warna
    val bgColor = if (isDarkTheme) SurfaceDark else SoftGrayBg
    val cardColor = if (isDarkTheme) CardBackgroundDark else CardBackgroundLight
    val textColor = if (isDarkTheme) Color.White else Color(0xFF1D1B20)

    val activityList = listOf(
        ActivityItem("Langkah", "2.44 km", "Target: 5.0 km", Icons.Default.DirectionsWalk),
        ActivityItem("Lari Pagi", "3.5 km", "Target: 3.0 km", Icons.Default.DirectionsRun),
        ActivityItem("Bersepeda", "10.2 km", "Target: 15.0 km", Icons.Default.PedalBike),
        ActivityItem("Minum Air", "1.5 Liter", "Target: 2.5 Liter", Icons.Default.LocalDrink),
        ActivityItem("Tidur", "6 Jam", "Target: 8 Jam", Icons.Default.NightsStay)
    )
    val currentDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID")).format(Date())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        // HEADER MODERN (Lurus, bukan melengkung, sesuai gaya Home baru)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Aktivitas", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = textColor)
                Text(currentDate, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
            // ICON CALENDAR DI SINI
            IconButton(onClick = { /* Show Calendar */ }) {
                Icon(Icons.Default.CalendarToday, contentDescription = "Calendar", tint = textColor, modifier = Modifier.size(28.dp))
            }
        }

        // LIST AKTIVITAS (Style Kartu Modern)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(activityList) { activity ->
                ActivityCardModern(activity, cardColor, textColor)
            }
        }
    }
}

@Composable
fun ActivityCardModern(activity: ActivityItem, cardColor: Color, textColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon dengan Background Lime
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = ModernLime.copy(alpha = 0.2f),
                modifier = Modifier.size(50.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(activity.icon, contentDescription = null, tint = PrimaryTeal, modifier = Modifier.size(24.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(activity.title, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
                Text(activity.value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = textColor)
                Text(activity.target, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
        }
    }
}