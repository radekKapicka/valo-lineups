package com.example.valo_lineups.ui.basic.bottomNavBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.valo_lineups.data.database.viewModels.DataViewModel
import com.example.valo_lineups.data.database.viewModels.LineupsDataViewModel
import com.example.valo_lineups.ui.HomeScreen
import com.example.valo_lineups.ui.async.agents.AgentMapScreen
import com.example.valo_lineups.ui.async.lineups.LineupDetailScreen
import com.example.valo_lineups.ui.async.lineups.LineupsScreen


@Composable
fun BottomNavGraph(navController: NavHostController){
    val viewModel: DataViewModel = DataViewModel()
    val lineupsviewModel: LineupsDataViewModel = LineupsDataViewModel()
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
            //LineupsScreen()
        }

        composable(
            route = DestinationAgentMap,
            arguments = listOf(navArgument(ArgMapId) { type = NavType.StringType })
        ){ backStackEntry ->
            backStackEntry.arguments?.getString(ArgMapId)?.let { AgentMapScreen(it, viewModel, navController) }
        }

        composable(
            route = DestinationAgentMapLineup,
            arguments = listOf(
                navArgument(ArgMapId) { type = NavType.StringType },
                navArgument(ArgAgentId) { type = NavType.StringType }
            ),
        ){ backStackEntry ->

            val Lineups =  LineupsScreen(
                mapId = backStackEntry.arguments?.getString(ArgMapId)!!,
                agentId = backStackEntry.arguments?.getString(ArgAgentId)!!,
                dataViewModel = lineupsviewModel,
                navHostController = navController,
            )

        }

        composable(
            route = DestinationLineupDetail,
            arguments = listOf(navArgument(ArgLineupId) { type = NavType.StringType })
        ){ backStackEntry ->
            backStackEntry.arguments?.getString(ArgLineupId)?.let { LineupDetailScreen(
                lineupId = it,
                navHostController = navController,
            )}
        }

    }

}

fun NavHostController.navigateDestinationAgentMapScreen(mapId: String){
    this.navigate(DestinationAgentMap.replaceArg(ArgMapId, mapId))
}

fun NavHostController.navigateDestinationLineupDetail(lineupId: String){
    this.navigate(DestinationLineupDetail.replaceArgLineup(ArgLineupId, lineupId))
}

fun NavHostController.navigateDestinationAgentMapLineupScreen(mapId: String, agentId: String){
    this.navigate(DestinationAgentMapLineup.replaceArg(ArgMapId, mapId).replaceArgAgent(ArgAgentId, agentId))
}

fun NavHostController.navigateLineups(){
    navigate(DestinationLineups)
}

private fun String.replaceArg(argName:String, newString: String) =
    replace("{$ArgMapId}", newString)

private fun String.replaceArgAgent(argName:String, newString: String) =
    replace("{$ArgAgentId}", newString)

private fun String.replaceArgLineup(argName:String, newString: String) =
    replace("{$ArgLineupId}", newString)

private const val DestinationHome = "home"

private const val DestinationLineups = "lineups"

private const val ArgMapId = "argMapId"
private const val ArgAgentId = "argAgentId"
private const val ArgLineupId = "argLineupId"
private const val DestinationAgentMap = "maps/{$ArgMapId}"
private const val DestinationAgentMapLineup = "maps/{$ArgMapId}/{$ArgAgentId}"
private const val DestinationLineupDetail = "lineups/{$ArgLineupId}"