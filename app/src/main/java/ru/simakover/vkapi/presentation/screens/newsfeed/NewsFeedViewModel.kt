package ru.simakover.vkapi.presentation.screens.newsfeed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.simakover.vkapi.data.repository.VkRepository
import ru.simakover.vkapi.domain.models.FeedPost

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {


    private val initialState = NewsFeedScreenState.Initial

    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    private val repository = VkRepository(application)

    init {
        _screenState.value = NewsFeedScreenState.Loading
        loadRecommendations()
    }

    private fun loadRecommendations() {
        viewModelScope.launch {
            val feedPosts = repository.loadRecommendations()
            _screenState.value = NewsFeedScreenState.Posts(posts = feedPosts, nextDataIsLoading = false)
        }
    }

    fun loadNextRecommendations() {
        viewModelScope.launch {
            _screenState.value = NewsFeedScreenState.Posts(
                posts = repository.feedPosts,
                nextDataIsLoading = true
            )
            loadRecommendations()
        }
    }

    fun changeLikeStatus(post: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(post)
            _screenState.value = NewsFeedScreenState.Posts(posts = repository.feedPosts)
        }
    }

    fun deletePost(post: FeedPost) {
        viewModelScope.launch {
            repository.ignoreRecommendation(post)
            _screenState.value = NewsFeedScreenState.Posts(posts = repository.feedPosts)
        }
    }
}