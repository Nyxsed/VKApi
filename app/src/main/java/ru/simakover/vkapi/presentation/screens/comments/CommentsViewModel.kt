package ru.simakover.vkapi.presentation.screens.comments

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.usecases.GetCommentsUseCase

class CommentsViewModel(
    feedPost: FeedPost,
    private val getCommentsScreenState: GetCommentsUseCase,
) : ViewModel() {

    val screenState = getCommentsScreenState(feedPost)
        .filter { it.isNotEmpty() }
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                postComments = it
            )
        }
}