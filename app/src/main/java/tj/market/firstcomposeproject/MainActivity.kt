package tj.market.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import tj.market.firstcomposeproject.first.BottomNavItem
import tj.market.firstcomposeproject.first.SettingsScreen
import tj.market.firstcomposeproject.ui.theme.FirstComposeProjectTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {

            val items = listOf(
                BottomNavItem.Home,
                BottomNavItem.Profile,
                BottomNavItem.Settings
            )

            val navHostController = rememberNavController()

            FirstComposeProjectTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val selectedItemPosition = remember {
                                mutableIntStateOf(0)
                            }
                            items.forEach {
                                BottomNavigationItem(
                                    icon = { Icon(it.icon, contentDescription = null) },
                                    label = { Text(it.label) },
                                    selected = selectedItemPosition.intValue == items.indexOf(it),
                                    onClick = {
                                        selectedItemPosition.intValue = items.indexOf(it)
                                    }
                                )
                            }

                        }
                    },
                    modifier = Modifier.fillMaxSize(),

                    ) { innerPadding ->

                    SettingsScreen()

//                    AppNavGraph(
//                        navController = navHostController,
//                        homeScreenContent = { HomeScreen(innerPadding) },
//                        settingsScreenContent = { SettingsScreen() },
//                        profileScreenContent = { ProfileScreen() }
//                    )

                }
            }
        }
    }
}
