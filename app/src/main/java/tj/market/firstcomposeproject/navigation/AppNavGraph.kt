package tj.market.firstcomposeproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    settingsScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            homeScreenContent()
        }

        composable(Screen.Settings.route) {
            settingsScreenContent()
        }
        composable(Screen.Profile.route) {
            profileScreenContent()
        }


    }
}