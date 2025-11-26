package id.antasari.p7_modern_ui_230104040210.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.antasari.p7_modern_ui_230104040210.ui.theme.CardBackgroundLight
import id.antasari.p7_modern_ui_230104040210.ui.theme.P7_modern_ui_230104040210Theme

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface, // Menggunakan warna surface dari tema
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Radius sudut sesuai desain
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // Sedikit shadow
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding),
                content = content
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAppCard() {
    P7_modern_ui_230104040210Theme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppCard {
                Text(text = "Ini adalah contoh AppCard sederhana.", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Bisa dipakai untuk berbagai konten.", style = MaterialTheme.typography.bodySmall)
            }
            AppCard(backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)) {
                Text(text = "Card dengan warna background berbeda.", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}