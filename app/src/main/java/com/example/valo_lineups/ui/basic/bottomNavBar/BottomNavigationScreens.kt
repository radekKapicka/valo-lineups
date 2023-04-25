package com.example.valo_lineups.ui.basic.bottomNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavigationScreens(val route: String, val title: String, val icon: ImageVector) {

    object Home : BottomNavigationScreens(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Lineups : BottomNavigationScreens(
        route = "lineups ",
        title = "Lineups ",
        icon = Icons.Default.ArrowBack
    )
}