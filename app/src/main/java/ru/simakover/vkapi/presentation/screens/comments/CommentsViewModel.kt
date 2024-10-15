package ru.simakover.vkapi.presentation.screens.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.simakover.vkapi.data.repository.VkRepository
import ru.simakover.vkapi.domain.models.FeedPost

class CommentsViewModel(feedPost: FeedPost, application: Application): AndroidViewModel(application)
 {

    private val _screenState =  MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

     private val repository = VkRepository(application)

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.loadPostComments(
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
            _screenState.value = CommentsScreenState.Comments(
                feedPost = feedPost,
                postComments = repository.postComments
            )
        }
    }
}