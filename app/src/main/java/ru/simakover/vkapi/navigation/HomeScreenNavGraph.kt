package ru.simakover.vkapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.google.gson.Gson
import ru.simakover.vkapi.domain.models.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
) {
    navigation(
        route = Screen.Home.route,
        startDestination = Screen.NewsFeed.route,
    ) {
        composable(route = Screen.NewsFeed.route) { newsFeedScreenContent() }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST) {
                    type = NavType.StringType
                }
            )
        ) {
            val feedPostJson = it.arguments?.getString(Screen.KEY_FEED_POST) ?: ""
            val feedPost = Gson().fromJson<FeedPost>(feedPostJson, FeedPost::class.java)
            commentsScreenContent(feedPost)
        }
    }
}