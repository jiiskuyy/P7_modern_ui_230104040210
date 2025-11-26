package id.antasari.p7_modern_ui_230104040210

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.antasari.p7_modern_ui_230104040210.model.BottomNavItem
import id.antasari.p7_modern_ui_230104040210.ui.screen.*

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screen_route,
        modifier = modifier
    ) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(
                navController = navController,
                isDarkTheme = isDarkTheme // Kirim status dark mode
            )
        }
        composable(BottomNavItem.Activity.screen_route) {
            ActivityScreen(isDarkTheme = isDarkTheme)
        }
        composable(BottomNavItem.Friends.screen_route) {
            FriendsScreen() // Friends screen biasanya aman karena pakai default background, tapi bisa diupdate jika perlu
        }
        composable(BottomNavItem.Leaderboard.screen_route) {
            LeaderboardScreen(isDarkTheme = isDarkTheme)
        }
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen(
                onSettingsClick = { navController.navigate("settings") },
                isDarkTheme = isDarkTheme // Kirim status dark mode
            )
        }

        composable("settings") {
            SettingsScreen(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }
    }
}