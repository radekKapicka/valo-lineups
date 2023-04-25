package com.example.valo_lineups.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.valo_lineups.ui.async.lineups.LineupsScreen
import com.example.valo_lineups.ui.basic.bottomNavBar.BottomNavBarScreen


@Composable
fun AppContainer(
    controller: NavHostController
) {
    //HomeScreen()

    NavHost(navController = controller, startDestination = DestinationHome){
        composable(
            route = DestinationHome
        ){
            BottomNavBarScreen(controller)
        }

        composable(
            route = DestinationRocketLaunches
        ){
            /*RocketLaunchesScreen(
                onNavigateDetail = {rocketId ->
                    controller.navigateRocketDetailScreen(rocketId)
                }
            )*/
        }

        composable(
            route = DestinationRocketDetail,
            arguments = listOf(navArgument(ArgRocketId) { type = NavType.StringType })
        ){ backStackEntry ->
            //RocketDetailScreen(backStackEntry.arguments?.getString(ArgRocketId))
        }
    }
}

fun NavHostController.navigateRocketLaunchesScreen(){
    this.navigate(DestinationRocketLaunches)
}

fun NavHostController.navigateRocketDetailScreen(rocketId: String){
    this.navigate(DestinationRocketDetail.replaceArg(ArgRocketId, rocketId))
}

fun NavHostController.navigateDatabaseScreen(){
    navigate(DestinationDatabaseScreen)
}

fun NavHostController.navigateLineups(){
    navigate(DestinationLineups)
}

private fun String.replaceArg(argName:String, newString: String) =
    replace("{$ArgRocketId}", newString)

private const val ArgRocketId = "argRocketId"

private const val DestinationHome = "home"
private const val DestinationRocketLaunches = "rocket-launches"
private const val DestinationRocketDetail = "rocket/{$ArgRocketId}"
private const val DestinationDatabaseScreen = "database"
private const val DestinationLineups = "lineups"