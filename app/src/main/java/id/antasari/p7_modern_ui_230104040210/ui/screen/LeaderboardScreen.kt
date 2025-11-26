package id.antasari.p7_modern_ui_230104040210.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.antasari.p7_modern_ui_230104040210.ui.theme.CardBackgroundDark
import id.antasari.p7_modern_ui_230104040210.ui.theme.CardBackgroundLight
import id.antasari.p7_modern_ui_230104040210.ui.theme.Orange
import id.antasari.p7_modern_ui_230104040210.ui.theme.PrimaryTeal
import id.antasari.p7_modern_ui_230104040210.ui.theme.SecondaryLime
import id.antasari.p7_modern_ui_230104040210.ui.theme.SurfaceDark
import id.antasari.p7_modern_ui_230104040210.ui.theme.TextBlack
import id.antasari.p7_modern_ui_230104040210.ui.theme.TextWhite

data class LeaderboardUser(
    val rank: Int,
    val name: String,
    val points: Int,
    val isUser: Boolean = false
)

@Composable
fun LeaderboardScreen(isDarkTheme: Boolean) {
    var selectedTab by remember { mutableStateOf(0) }

    // Warna adaptif
    val bgColor = if (isDarkTheme) SurfaceDark else PrimaryTeal
    val listCardColor = if (isDarkTheme) CardBackgroundDark else CardBackgroundLight
    val listTextColor = if (isDarkTheme) TextWhite else TextBlack

    val leaderboardData = listOf(
        LeaderboardUser(1, "Rey", 2000),
        LeaderboardUser(2, "Ayah", 1990),
        LeaderboardUser(3, "Kaka", 1930),
        LeaderboardUser(4, "Muhammad Hifzi", 1900, isUser = true),
        LeaderboardUser(5, "Adek", 1000),
        LeaderboardUser(6, "Basim", 1000),
        LeaderboardUser(7, "Siti", 850)
    )

    val topThree = leaderboardData.take(3)
    val restOfList = leaderboardData.drop(3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. HEADER
        Text(
            text = "LEADERBOARD",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite, // Tetap putih karena background Teal/Dark
            letterSpacing = 2.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        // 2. TOGGLE
        LeaderboardToggle(
            selectedIndex = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 3. USER STATS CARD (Lime)
        Card(
            modifier = Modifier.fillMaxWidth().height(80.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SecondaryLime)
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("#4", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold, color = TextWhite)
                Spacer(modifier = Modifier.width(16.dp))
                Text("Kamu melakukan 60% lebih baik dari seluruh list temanmu", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = TextWhite)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 4. LIST & PODIUM
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            item {
                PodiumSection(topThree)
                Spacer(modifier = Modifier.height(16.dp))
            }

            itemsIndexed(restOfList) { _, user ->
                LeaderboardListItem(user, listCardColor, listTextColor)
            }
        }
    }
}

@Composable
fun LeaderboardToggle(selectedIndex: Int, onTabSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp).clip(RoundedCornerShape(25.dp)).background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf("MONTHLY", "ALL TIME").forEachIndexed { index, text ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(if (selectedIndex == index) SecondaryLime else Color.Transparent)
                    .clickable { onTabSelected(index) },
                contentAlignment = Alignment.Center
            ) {
                Text(text, fontWeight = FontWeight.Bold, color = if (selectedIndex == index) TextWhite else TextBlack)
            }
        }
    }
}

@Composable
fun PodiumSection(users: List<LeaderboardUser>) {
    val rank1 = users.find { it.rank == 1 }
    val rank2 = users.find { it.rank == 2 }
    val rank3 = users.find { it.rank == 3 }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        if (rank2 != null) PodiumItem(rank2, 120.dp, Color(0xFF00E676))
        if (rank1 != null) PodiumItem(rank1, 160.dp, Color(0xFF00BFA5), true)
        if (rank3 != null) PodiumItem(rank3, 100.dp, Color(0xFF69F0AE))
    }
}

@Composable
fun PodiumItem(user: LeaderboardUser, height: androidx.compose.ui.unit.Dp, color: Color, isFirst: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 4.dp)) {
        Box(
            modifier = Modifier.size(if (isFirst) 70.dp else 50.dp).clip(CircleShape).background(Orange),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = TextWhite, modifier = Modifier.size(if (isFirst) 40.dp else 30.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(user.name, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = TextWhite)
        Text("${user.points} pts", style = MaterialTheme.typography.labelSmall, color = TextWhite.copy(alpha = 0.8f))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier.width(if (isFirst) 100.dp else 80.dp).height(height).clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)).background(color),
            contentAlignment = Alignment.Center
        ) {
            Text("#${user.rank}", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = TextWhite.copy(alpha = 0.5f))
        }
    }
}

@Composable
fun LeaderboardListItem(user: LeaderboardUser, cardColor: Color, textColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().height(70.dp),
        shape = RoundedCornerShape(35.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(45.dp).clip(CircleShape).background(Orange), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = TextWhite)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(user.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = textColor)
                    Text("${user.points} point", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic, color = Color.Gray)
                }
            }
            Text("#${user.rank}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Gray)
        }
    }
}