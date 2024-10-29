package ru.simakover.vkapi.presentation.screens.comments

import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.entity.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val postComments: List<PostComment>,
    ) : CommentsScreenState()

}