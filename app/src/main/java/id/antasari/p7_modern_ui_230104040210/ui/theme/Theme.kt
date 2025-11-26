package id.antasari.p7_modern_ui_230104040210.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Skema warna Gelap
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryTeal,
    secondary = SecondaryLime,
    tertiary = Pink80,
    background = SurfaceDark,
    surface = SurfaceDark,
    onPrimary = TextWhite,
    onBackground = TextWhite
)

// Skema warna Terang
private val LightColorScheme = lightColorScheme(
    primary = PrimaryTeal,
    secondary = SecondaryLime,
    tertiary = Pink40,
    background = BackgroundWhite,
    surface = BackgroundWhite,
    onPrimary = TextWhite,
    onBackground = TextBlack
)

@Composable
fun P7_modern_ui_230104040210Theme( // <-- Pastikan nama ini SAMA dengan di MainActivity
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Pastikan file Type.kt ada, jika error hapus baris ini
        content = content
    )
}