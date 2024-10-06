package ru.simakover.vkapi.presentation.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.simakover.vkapi.navigation.AppNavGraph
import ru.simakover.vkapi.navigation.NavigationState
import ru.simakover.vkapi.navigation.Screen
import ru.simakover.vkapi.navigation.rememberNavigationState
import ru.simakover.vkapi.presentation.MainViewModel


@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    //Self made!!
    val navigationState = rememberNavigationState()
    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            MainNavBar(
                currentRoute = currentRoute,
                navItemOnClickListener = {
                    navigationState.navigateTo(it.screen.route)
                }
            )
        },
    ) { paddings ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                HomeScreen(
                    viewModel = viewModel,
                    modifier = Modifier
                        .padding(paddings)
                )
            },
            favouriteScreenContent = {
                TextCounter(name = "Favourites")
            },
            profileScreenContent = {
                TextCounter(name = "Profile")
            }
        )
    }
}

@Composable
fun TextCounter(
    name:String
) {
    var count by rememberSaveable { mutableStateOf(0) }

    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}