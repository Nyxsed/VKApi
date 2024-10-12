package ru.simakover.vkapi.presentation.screens.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.simakover.vkapi.domain.models.FeedPost

class CommentsViewModelFactory(
    private val feedPost: FeedPost,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(
            feedPost = feedPost
        ) as T
    }
}