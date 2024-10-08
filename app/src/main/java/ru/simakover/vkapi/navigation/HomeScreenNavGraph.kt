package ru.simakover.vkapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit,
){
    navigation(
        route = Screen.Home.route,
        startDestination = Screen.NewsFeed.route,
    ) {
        composable(route = Screen.NewsFeed.route) { newsFeedScreenContent() }
        composable(route = Screen.Comments.route) { commentsScreenContent() }
    }
}