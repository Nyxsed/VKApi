package ru.simakover.vkapi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.simakover.vkapi.domain.models.FeedPost
import ru.simakover.vkapi.domain.models.PostComment
import ru.simakover.vkapi.presentation.ui.screens.comments.CommentsScreenState

class CommentsViewModel: ViewModel() {

    private val _screenState =  MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(FeedPost())
    }

    fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(element = PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            postComments = comments
        )
    }
}