package ru.simakover.vkapi.domain.usecases

import ru.simakover.vkapi.domain.repository.VkRepository

class CheckAuthUseCase(
    private val repository: VkRepository
) {
    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}