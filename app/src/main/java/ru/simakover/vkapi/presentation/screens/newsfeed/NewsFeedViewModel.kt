package ru.simakover.vkapi.presentation.screens.newsfeed

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.simakover.vkapi.data.repository.VkRepository
import ru.simakover.vkapi.domain.models.FeedPost
import ru.simakover.vkapi.presentation.util.Util.mergeWith

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VkRepository(application)

    private val recommendationsFlow = repository.recommendations
    private val loadNextDataFlow = MutableSharedFlow<NewsFeedScreenState>()

    private val exceptionHandler = CoroutineExceptionHandler {_,_ ->
        Log.d("NewsFeedViewModel", "Exception caught by CoroutineExceptionHandler")
    }

    val screenState = recommendationsFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(posts = it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(loadNextDataFlow)

    fun loadNextRecommendations() {
        viewModelScope.launch {
            loadNextDataFlow.emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationsFlow.value,
                    nextDataIsLoading = true
                )
            )
            repository.loadNextData()
        }
    }

    fun changeLikeStatus(post: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            repository.changeLikeStatus(post)
        }
    }

    fun deletePost(post: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            repository.deletePost(post)
        }
    }
}