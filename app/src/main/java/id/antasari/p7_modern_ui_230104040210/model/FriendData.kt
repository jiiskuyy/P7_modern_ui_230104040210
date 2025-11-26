package id.antasari.p7_modern_ui_230104040210.model

import androidx.compose.ui.graphics.Color
// Pastikan import ini sesuai dengan package Color.kt kamu
import id.antasari.p7_modern_ui_230104040210.ui.theme.* data class Friend(
    val id: Int,
    val name: String,
    val phone: String,
    val color: Color
)

val dummyFriends = listOf(
    Friend(1, "Antasari", "08123456789", PrimaryTeal),
    Friend(2, "Budi Santoso", "08567890123", SecondaryLime),
    Friend(3, "Siti Aminah", "08134567890", Orange),
    Friend(4, "Rina Wati", "08987654321", Rank1Color)
)