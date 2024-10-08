package ru.simakover.vkapi.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.simakover.vkapi.domain.models.FeedPost
import ru.simakover.vkapi.navigation.AppNavGraph
import ru.simakover.vkapi.navigation.rememberNavigationState
import ru.simakover.vkapi.presentation.viewmodels.NewsFeedViewModel
import ru.simakover.vkapi.presentation.ui.elements.MainNavBar
import ru.simakover.vkapi.presentation.ui.screens.comments.CommentsScreen
import ru.simakover.vkapi.presentation.ui.screens.home.HomeScreen


@Composable
fun MainScreen() {
    //Self made!!
    val navigationState = rememberNavigationState()
    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

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
                if(commentsToPost.value == null) {
                    HomeScreen(
                        modifier = Modifier
                            .padding(paddings),
                        onCommentClickListener = {
                            commentsToPost.value = it
                        }
                    )
                } else {
                    CommentsScreen(
                        onBackPressed = {
                            commentsToPost.value = null
                        },
                        feedPost = commentsToPost.value!!
                    )
                }
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