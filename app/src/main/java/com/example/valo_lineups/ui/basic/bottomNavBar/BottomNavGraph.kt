package com.example.valo_lineups.ui.basic.bottomNavBar

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.valo_lineups.data.DataViewModel
import com.example.valo_lineups.ui.HomeScreen
import com.example.valo_lineups.ui.async.lineups.LineupsScreen

@Composable
fun BottomNavGraph(navController: NavHostController){
    val viewModel: DataViewModel = DataViewModel()
    NavHost(navController = navController,
        startDestination = BottomNavigationScreens.Home.route
    ){
        composable(route = BottomNavigationScreens.Home.route){
            HomeScreen(viewModel, navController)
        }
        composable(route = BottomNavigationScreens.Lineups.route){
            LineupsScreen()
        }
    }

}