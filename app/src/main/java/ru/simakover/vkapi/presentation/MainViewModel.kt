package ru.simakover.vkapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.simakover.vkapi.domain.PostItem
import ru.simakover.vkapi.domain.StatisticItem

class MainViewModel : ViewModel() {

    private val initialList = mutableListOf<PostItem>().apply {
        repeat(5) {
            add(PostItem(id = it))
        }
    }

    private val _postList = MutableLiveData<List<PostItem>>(initialList)
    val postList: LiveData<List<PostItem>> = _postList

    fun updateCount(post: PostItem, item: StatisticItem) {
        val oldPosts = postList.value?.toMutableList() ?: mutableListOf()

        val oldStatistics = post.statistics ?: throw IllegalStateException()
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
        _postList.value = oldPosts.apply {
            replaceAll { oldPost ->
                if (oldPost.id == updatedPost.id) {
                    updatedPost
                } else {
                    oldPost
                }
            }
        }
    }

    fun deletePost(post: PostItem) {
        val modifiedList = _postList.value?.toMutableList() ?: mutableListOf()
        modifiedList.remove(post)
        _postList.value = modifiedList
    }
}