package ru.simakover.vkapi.domain.usecases

import ru.simakover.vkapi.domain.repository.VkRepository

class LoadNextDataUseCase(
    private val repository: VkRepository
) {
    suspend operator fun invoke() {
        repository.loadNextData()
    }
}