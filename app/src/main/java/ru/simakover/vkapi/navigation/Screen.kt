package ru.simakover.vkapi.navigation

import ru.simakover.vkapi.domain.models.FeedPost

sealed class Screen(
    val route: String,
) {

    object Home: Screen(ROUTE_HOME)
    object NewsFeed: Screen(ROUTE_NEWS_FEED)
    object Comments: Screen(ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "comments"

        fun  getRouteWithArgs(feedPost: FeedPost) : String {
            return "$ROUTE_FOR_ARGS/${feedPost.id}"
        }
    }

    object Favourite: Screen(ROUTE_FAVOURITE)
    object Profile: Screen(ROUTE_PROFILE)


    companion object {
        const val KEY_FEED_POST_ID = "feed_post_id"

        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST_ID}"
        const val ROUTE_NEWS_FEED = "news_feed"

        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}