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
import ru.simakover.vkapi.data.repository.VkRepositoryImpl
import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.usecases.ChangeLikeStatusUseCase
import ru.simakover.vkapi.domain.usecases.DeletePostUseCase
import ru.simakover.vkapi.domain.usecases.GetRecommendationsUseCase
import ru.simakover.vkapi.domain.usecases.LoadNextDataUseCase
import ru.simakover.vkapi.presentation.util.Util.mergeWith

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VkRepositoryImpl(application)

    private val getRecommendationsUseCase = GetRecommendationsUseCase(repository)
    private val loadNextDataUseCase = LoadNextDataUseCase(repository)
    private val changeLikeStatusUseCase = ChangeLikeStatusUseCase(repository)
    private val deletePostUseCase = DeletePostUseCase(repository)

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