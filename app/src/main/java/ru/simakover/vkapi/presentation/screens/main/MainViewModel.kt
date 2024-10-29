package ru.simakover.vkapi.presentation.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.simakover.vkapi.data.repository.VkRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VkRepository(application)
    val authState = repository.authStateFlow

    fun performAuthResult() {
        viewModelScope.launch {
            repository.checkAuthState()
        }
    }
}