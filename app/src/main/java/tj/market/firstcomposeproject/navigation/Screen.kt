package tj.market.firstcomposeproject.navigation

sealed class Screen(val route: String) {

    object Home : Screen(Route_Home)
    object Profile : Screen(Route_Profile)
    object Settings : Screen(Route_Settings)

    private companion object {
        const val Route_Home = "home"
        const val Route_Profile = "profile"
        const val Route_Settings = "settings"
    }

}