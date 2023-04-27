package com.example.valo_lineups.ui.basic.bottomNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavigationScreens(val route: String, val title: String, val icon: ImageVector) {

    object Lineups : BottomNavigationScreens(
        route = "lineups ",
        title = "Lineups ",
        icon = Icons.Default.ArrowBack
    )
    object Home : BottomNavigationScreens(
        route = "homeLineups",
        title = "Home Lineups",
        icon = Icons.Default.Home
    )
}