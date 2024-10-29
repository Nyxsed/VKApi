package ru.simakover.vkapi.presentation.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.simakover.vkapi.data.repository.VkRepositoryImpl
import ru.simakover.vkapi.domain.usecases.CheckAuthUseCase
import ru.simakover.vkapi.domain.usecases.GetAuthStateFlowUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VkRepositoryImpl(application)

    private val getAuthStateFlowUseCase = GetAuthStateFlowUseCase(repository)
    private val checkAuthStateUseCase = CheckAuthUseCase(repository)

    val authState = getAuthStateFlowUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateUseCase()
        }
    }
}