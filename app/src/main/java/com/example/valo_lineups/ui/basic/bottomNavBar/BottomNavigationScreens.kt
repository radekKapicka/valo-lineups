package com.example.valo_lineups.ui.basic.bottomNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.RequestPage
import androidx.compose.material.icons.filled.RequestQuote
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavigationScreens(val route: String, val title: String, val icon: ImageVector) {

    object RequestLineups : BottomNavigationScreens(
        route = "requestLineups",
        title = "Request Lineups",
        icon = Icons.Default.QuestionMark
    )
    object Home : BottomNavigationScreens(
        route = "homeLineups",
        title = "Home Lineups",
        icon = Icons.Default.Home
    )

    object RequestedByUsers : BottomNavigationScreens(
        route = "requestedByUsers",
        title = "Request List",
        icon = Icons.Default.ListAlt
    )
}