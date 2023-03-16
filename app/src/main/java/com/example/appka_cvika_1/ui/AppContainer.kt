package com.example.appka_cvika_1.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appka_cvika_1.ui.async.launches.RocketLaunchesScreen
import com.example.appka_cvika_1.ui.async.rocketDetail.RocketDetailScreen
import com.example.appka_cvika_1.ui.database.DatabaseScreen

@Composable
fun AppContainer(
    controller: NavHostController
) {
    //HomeScreen()

    NavHost(navController = controller, startDestination = DestinationHome){
        composable(
            route = DestinationHome
        ){
            HomeScreen(controller)
        }

        composable(
            route = DestinationRocketLaunches
        ){
            RocketLaunchesScreen(
                onNavigateDetail = {rocketId ->
                    controller.navigateRocketDetailScreen(rocketId)
                }
            )
        }

        composable(
            route = DestinationRocketDetail,
            arguments = listOf(navArgument(ArgRocketId) { type = NavType.StringType })
        ){ backStackEntry ->
            RocketDetailScreen(backStackEntry.arguments?.getString(ArgRocketId))
        }

        composable(
            route = DestinationDatabaseScreen,
        ){ backStackEntry ->
            DatabaseScreen()
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

private fun String.replaceArg(argName:String, newString: String) =
    replace("{$ArgRocketId}", newString)

private const val ArgRocketId = "argRocketId"

private const val DestinationHome = "home"
private const val DestinationRocketLaunches = "rocket-launches"
private const val DestinationRocketDetail = "rocket/{$ArgRocketId}"
private const val DestinationDatabaseScreen = "database"