package ru.simakover.vkapi.presentation.ui.screens.home

import ru.simakover.vkapi.domain.models.FeedPost

sealed class NewsFeedScreenState {

    object  Initial: NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()

}