package ru.simakover.vkapi.presentation.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.simakover.vkapi.navigation.AppNavGraph
import ru.simakover.vkapi.navigation.rememberNavigationState
import ru.simakover.vkapi.presentation.screens.comments.CommentsScreen
import ru.simakover.vkapi.presentation.screens.newsfeed.NewsFeedScreen


@Composable
fun MainScreen() {
    //Self made!!
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {

            val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

            NavigationBar {
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourites,
                    NavigationItem.Profile
                )
                items.forEach { item ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = null)
                        },
                        label = {
                            Text(stringResource(id = item.titleResId))
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        )
                    )
                }
            }
        },
    ) { paddings ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                NewsFeedScreen(
                    modifier = Modifier
                        .padding(paddings),
                    onCommentClickListener = { feedPost ->
                    navigationState.navigateToComments(feedPost)
                    }
                )
            },
            favouriteScreenContent = {
                TextCounter(name = "Favourites")
            },
            profileScreenContent = {
                TextCounter(name = "Profile")
            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = feedPost
                )
            }
        )
    }
}

@Composable
fun TextCounter(
    name: String,
) {
    var count by rememberSaveable { mutableStateOf(0) }

    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}