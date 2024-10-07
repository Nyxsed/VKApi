package ru.simakover.vkapi.presentation.ui.screens.comments

import ru.simakover.vkapi.domain.models.FeedPost
import ru.simakover.vkapi.domain.models.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val postComments: List<PostComment>,
    ) : CommentsScreenState()

}