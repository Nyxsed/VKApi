package ru.simakover.vkapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.simakover.vkapi.domain.PostItem
import ru.simakover.vkapi.domain.StatisticItem

class MainViewModel : ViewModel() {

    private val _postItem = MutableLiveData<PostItem>(PostItem())
    val postItem: LiveData<PostItem> = _postItem

    fun updateCount(item: StatisticItem) {
        val oldStatistics = postItem.value?.statistics ?: throw IllegalStateException()
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        _postItem.value = postItem.value?.copy(statistics = newStatistics)
    }
}