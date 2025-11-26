package com.example.myhealthapp.model

import androidx.compose.ui.graphics.Color

data class Friend(val id: Int, val name: String, val phone: String, val color: Color)
data class Ranker(val name: String, val points: Int, val rank: Int)

// Data Dummy
val dummyFriends = listOf(
    Friend(1, "Ayah", "+62812345678", Color(0xFFFFCC80)),
    Friend(2, "Kaka", "+62812345679", Color(0xFFFFF59D)),
    Friend(3, "Adek", "+62812345670", Color(0xFFA5D6A7)),
    Friend(4, "Rey", "+62812345671", Color(0xFF90CAF9)),
    Friend(5, "Basim", "+62812345672", Color(0xFFCE93D8))
)

val dummyRankers = listOf(
    Ranker("Rey", 2000, 1),
    Ranker("Ayah", 1990, 2),
    Ranker("Kaka", 1930, 3),
    Ranker("Azmi", 1900, 4),
    Ranker("Adek", 1000, 5),
    Ranker("Basim", 900, 6)
)