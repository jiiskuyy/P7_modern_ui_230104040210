package id.antasari.p7_modern_ui_230104040210.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val screen_route: String) {
    object Home : BottomNavItem("Home", Icons.Default.Home, "home")
    object Activity : BottomNavItem("Activity", Icons.Default.List, "activity")
    object Friends : BottomNavItem("Friends", Icons.Default.Person, "friends")
    object Leaderboard : BottomNavItem("Rank", Icons.Default.EmojiEvents, "leaderboard")

    // GANTI SHOP MENJADI PROFILE
    object Profile : BottomNavItem("Profile", Icons.Default.AccountCircle, "profile")
}