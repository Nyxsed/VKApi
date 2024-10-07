package ru.simakover.vkapi.presentation.ui.screens

import ru.simakover.vkapi.domain.models.PostComment
import ru.simakover.vkapi.domain.models.PostItem

sealed class HomeScreenState {

    object  Initial: HomeScreenState()

    data class Posts(val posts: List<PostItem>): HomeScreenState()
    data class Comments(val post: PostItem,val comments: List<PostComment>): HomeScreenState()

}