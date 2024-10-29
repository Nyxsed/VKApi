package ru.simakover.vkapi.presentation.screens.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import ru.simakover.vkapi.data.repository.VkRepositoryImpl
import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.usecases.GetCommentsUseCase

class CommentsViewModel(feedPost: FeedPost, application: Application) : AndroidViewModel(application) {

    private val repository = VkRepositoryImpl(application)

    private val getCommentsScreenState = GetCommentsUseCase(repository)

    val screenState = getCommentsScreenState(feedPost)
        .filter { it.isNotEmpty() }
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                postComments = it
            )
        }
}