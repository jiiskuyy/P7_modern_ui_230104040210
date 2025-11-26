package id.antasari.p7_modern_ui_230104040210.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.antasari.p7_modern_ui_230104040210.model.dummyFriends
import id.antasari.p7_modern_ui_230104040210.ui.components.AppTextField
import id.antasari.p7_modern_ui_230104040210.ui.theme.AccentGreen
import id.antasari.p7_modern_ui_230104040210.ui.theme.Orange
import id.antasari.p7_modern_ui_230104040210.ui.theme.PrimaryTeal
import id.antasari.p7_modern_ui_230104040210.ui.theme.SecondaryLime
import id.antasari.p7_modern_ui_230104040210.ui.theme.TextBlack
import id.antasari.p7_modern_ui_230104040210.ui.theme.TextWhite

@Composable
fun FriendsScreen() {
    val searchText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Menggunakan LazyColumn agar Header ikut ter-scroll jika layar pendek
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            // ITEM 1: HEADER CUSTOM (Profil + Search Bar Melayang)
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp) // Tinggi total area header termasuk search bar yg keluar
                ) {
                    // 1. Background Teal Melengkung
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(310.dp)
                            .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                            .background(PrimaryTeal)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 40.dp), // Beri ruang untuk search bar
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "TEMAN",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = TextWhite,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            // Avatar Besar dengan Background Kuning/Orange
                            Box(
                                modifier = Modifier
                                    .size(110.dp)
                                    .clip(CircleShape)
                                    .background(Orange),
                                contentAlignment = Alignment.Center
                            ) {
                                // Placeholder Gambar Kartun
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "User Avatar",
                                    tint = TextWhite,
                                    modifier = Modifier.size(70.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Nama User
                            Text(
                                text = "Hifzi",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = TextWhite
                            )

                            // Lokasi
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Location",
                                    tint = SecondaryLime,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Lokasi anda",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = SecondaryLime,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    // 2. Search Bar Melayang (Overlapping)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter) // Posisikan di bawah
                            .padding(horizontal = 24.dp)
                            .offset(y = (-10).dp) // Geser sedikit ke atas agar menumpuk garis lengkung
                    ) {
                        // Menggunakan AppTextField yang sudah kita buat sebelumnya
                        // Kita bungkus dengan Card/Surface untuk efek shadow yang kuat
                        Box(
                            modifier = Modifier.shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(12.dp),
                                spotColor = Color.Black.copy(alpha = 0.1f)
                            )
                        ) {
                            AppTextField(
                                value = searchText.value,
                                onValueChange = { searchText.value = it },
                                placeholder = "Cari",
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            // ITEM 2: SPACER JARAK
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // ITEM 3: LIST TEMAN
            items(dummyFriends) { friend ->
                FriendListItem(friend)
            }
        }
    }
}

@Composable
fun FriendListItem(friend: id.antasari.p7_modern_ui_230104040210.model.Friend) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .height(80.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar Teman
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Orange.copy(alpha = 0.2f)), // Background orange transparan
                contentAlignment = Alignment.Center
            ) {
                // Bisa diganti Image jika ada
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Orange,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Info Teman
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = friend.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextBlack
                )
                Text(
                    text = "+62 ${friend.phone}", // Format nomor HP
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }
    }
}