package ru.simakover.vkapi.domain.usecases

import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.repository.VkRepository

class DeletePostUseCase(
    private val repository: VkRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        repository.deletePost(feedPost)
    }
}