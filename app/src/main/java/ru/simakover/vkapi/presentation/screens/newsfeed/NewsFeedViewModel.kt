package ru.simakover.vkapi.presentation.screens.newsfeed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.usecases.ChangeLikeStatusUseCase
import ru.simakover.vkapi.domain.usecases.DeletePostUseCase
import ru.simakover.vkapi.domain.usecases.GetRecommendationsUseCase
import ru.simakover.vkapi.domain.usecases.LoadNextDataUseCase
import ru.simakover.vkapi.presentation.util.Util.mergeWith

class NewsFeedViewModel(
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase,
    private val deletePostUseCase: DeletePostUseCase,

    ) : ViewModel() {

    private val recommendationsFlow = getRecommendationsUseCase()
    private val loadNextDataFlow = MutableSharedFlow<NewsFeedScreenState>()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
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
            loadNextDataUseCase()
        }
    }

    fun changeLikeStatus(post: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(post)
        }
    }

    fun deletePost(post: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            deletePostUseCase(post)
        }
    }
}