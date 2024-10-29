package ru.simakover.vkapi.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.simakover.vkapi.domain.entity.AuthState
import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.entity.PostComment

interface VkRepository {
    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getRecommendations(): StateFlow<List<FeedPost>>

    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>

    suspend fun changeLikeStatus(post: FeedPost)

    suspend fun deletePost(post: FeedPost)

    suspend fun loadNextData()

    suspend fun checkAuthState()
}