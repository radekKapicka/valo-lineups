package com.example.valo_lineups.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.valo_lineups.data.database.viewModels.DataViewModel
import com.example.valo_lineups.ui.async.agents.AgentMapScreen
import com.example.valo_lineups.ui.async.lineups.LineupsScreen
import com.example.valo_lineups.ui.basic.bottomNavBar.BottomNavBarScreen


@Composable
fun AppContainer(
    controller: NavHostController,
viewModel: DataViewModel
) {
    //HomeScreen()

    NavHost(navController = controller, startDestination = DestinationHome){
        composable(
            route = DestinationHome
        ){
            BottomNavBarScreen()
        }


        composable(
            route = DestinationAgentMap,
            arguments = listOf(navArgument(ArgMapId) { type = NavType.StringType })
        ){ backStackEntry ->
            backStackEntry.arguments?.getString(ArgMapId)?.let { AgentMapScreen(it, viewModel ,controller) }
        }

    }
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
