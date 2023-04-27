package com.example.valo_lineups.ui.basic.bottomNavBar

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.valo_lineups.data.DataViewModel
import com.example.valo_lineups.ui.HomeScreen
import com.example.valo_lineups.ui.async.agents.AgentMapScreen
import com.example.valo_lineups.ui.async.lineups.LineupsScreen

@Composable
fun BottomNavGraph(navController: NavHostController){
    val viewModel: DataViewModel = DataViewModel()
    NavHost(navController = navController,
        startDestination = DestinationHome
    ){

        composable(
            route = DestinationHome
        ){
            HomeScreen(dataViewModel = viewModel, parentController = navController)
        }

        composable(route = BottomNavigationScreens.Home.route){
            HomeScreen(viewModel, navController)
        }
        composable(route = BottomNavigationScreens.Lineups.route){
            LineupsScreen()
        }

        composable(
            route = DestinationAgentMap,
            arguments = listOf(navArgument(ArgMapId) { type = NavType.StringType })
        ){ backStackEntry ->
            backStackEntry.arguments?.getString(ArgMapId)?.let { AgentMapScreen(it) }
        }

        composable(
            route = DestinationLineups
        ) {
            LineupsScreen()
        }
    }

}

fun NavHostController.navigateDestinationAgentMapScreen(mapId: String){
    this.navigate(DestinationAgentMap.replaceArg(ArgMapId, mapId))
}

fun NavHostController.navigateLineups(){
    navigate(DestinationLineups)
}

private fun String.replaceArg(argName:String, newString: String) =
    replace("{$ArgMapId}", newString)

private const val DestinationHome = "home"

private const val DestinationLineups = "lineups"

private const val ArgMapId = "argRocketId"
private const val DestinationAgentMap = "maps/{$ArgMapId}"