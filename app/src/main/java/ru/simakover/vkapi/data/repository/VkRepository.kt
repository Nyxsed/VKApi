package ru.simakover.vkapi.data.repository

import android.app.Application
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import ru.simakover.vkapi.data.mapper.VkMapper
import ru.simakover.vkapi.data.network.ApiFactory
import ru.simakover.vkapi.domain.models.FeedPost
import ru.simakover.vkapi.domain.models.PostComment
import ru.simakover.vkapi.domain.models.StatisticItem
import ru.simakover.vkapi.domain.models.StatisticType
import ru.simakover.vkapi.presentation.util.Util.mergeWith

class VkRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)

    private val apiService = ApiFactory.apiService
    private val mapper = VkMapper()

    private val _feedPosts = mutableListOf<FeedPost>()
    private val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private var nextFrom: String? = null

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Token is null")
    }

    private val scope = CoroutineScope(Dispatchers.Default)
    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)
    private val refreshedListFlow = MutableSharedFlow<List<FeedPost>>()
    private val loadedListFlow = flow {
        nextDataNeededEvents.emit(Unit)
        nextDataNeededEvents.collect {
            val startFrom = nextFrom

            if (startFrom == null && feedPosts.isNotEmpty()) {
                emit(feedPosts)
                return@collect
            }

            val response = if (startFrom == null) {
                apiService.loadRecommendations(getAccessToken())
            } else {
                apiService.loadRecommendations(getAccessToken(), startFrom)
            }

            nextFrom = response.newsFeedContent.nextFrom

            val posts = mapper.mapResponseToPosts(response)
            _feedPosts.addAll(posts)
            emit(feedPosts)
        }
    }

    val recommendations: StateFlow<List<FeedPost>> = loadedListFlow
        .mergeWith(refreshedListFlow)
        .stateIn(
            scope = scope,
            started = SharingStarted.Lazily,
            initialValue = feedPosts
        )

    suspend fun loadNextData() {
        nextDataNeededEvents.emit(Unit)
    }

    suspend fun changeLikeStatus(post: FeedPost) {
        val response = if (!post.isLiked) {
            apiService.addLike(
                token = getAccessToken(),
                ownerId = post.communityId,
                postId = post.id
            )
        } else {
            apiService.deleteLike(
                token = getAccessToken(),
                ownerId = post.communityId,
                postId = post.id
            )
        }

        val newLikesCount = response.likes.count

        val newStatistics = post.statistics.toMutableList().apply {
            removeIf { it.type == StatisticType.LIKES }
            add(StatisticItem(type = StatisticType.LIKES, count = newLikesCount))
        }

        val newPost = post.copy(
            statistics = newStatistics,
            isLiked = !post.isLiked
        )
        val postIndex = _feedPosts.indexOf(post)

        _feedPosts[postIndex] = newPost
        refreshedListFlow.emit(feedPosts)
    }

    suspend fun ignoreRecommendation(post: FeedPost) {
        apiService.ignoreRecommendation(
            token = getAccessToken(),
            ownerId = post.communityId,
            postId = post.id
        )
        _feedPosts.remove(post)
        refreshedListFlow.emit(feedPosts)
    }

    private val _postComments = mutableListOf<PostComment>()
    val postComments: List<PostComment>
        get() = _postComments.toList()

    suspend fun loadPostComments(ownerId: Long, postId: Long): List<PostComment> {

        val response = apiService.loadComments(
            token = getAccessToken(),
            ownerId = ownerId,
            postId = postId
        )

        val comments = mapper.mapResponseToComments(response)
        _postComments.addAll(comments)
        return postComments
    }
}