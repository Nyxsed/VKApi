package ru.simakover.vkapi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.simakover.vkapi.domain.models.PostComment
import ru.simakover.vkapi.domain.models.PostItem
import ru.simakover.vkapi.domain.models.StatisticItem
import ru.simakover.vkapi.presentation.ui.screens.HomeScreenState

class MainViewModel : ViewModel() {

    private val initialPostList = mutableListOf<PostItem>().apply {
        repeat(5) {
            add(PostItem(id = it))
        }
    }

    private val initialCommentList = mutableListOf<PostComment>().apply {
        repeat(10) {
            add(PostComment(id = it))
        }
    }

    private val initialState = HomeScreenState.Posts(posts = initialPostList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private var savedState: HomeScreenState? = initialState

    fun showComments(post: PostItem) {
        savedState = _screenState.value
        _screenState.value = HomeScreenState.Comments(post, initialCommentList)
    }

    fun closeComments() {
        _screenState.value = savedState!!
    }

    fun updateCount(post: PostItem, item: StatisticItem) {

        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()

        val oldStatistics = post.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }

        val updatedPost = post.copy(statistics = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll { oldPost ->
                if (oldPost.id == updatedPost.id) {
                    updatedPost
                } else {
                    oldPost
                }
            }
        }
        _screenState.value = HomeScreenState.Posts(posts = newPosts)
    }

    fun deletePost(post: PostItem) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val modifiedList = currentState.posts.toMutableList()
        modifiedList.remove(post)
        _screenState.value = HomeScreenState.Posts(posts = modifiedList)
    }
}