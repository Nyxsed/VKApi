package ru.simakover.vkapi.presentation.screens.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import ru.simakover.vkapi.data.repository.VkRepository
import ru.simakover.vkapi.domain.models.FeedPost

class CommentsViewModel(feedPost: FeedPost, application: Application) : AndroidViewModel(application) {

    private val repository = VkRepository(application)
    val screenState = repository.commentsFlow(feedPost)
        .filter { it.isNotEmpty() }
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                postComments = it
            )
        }
}