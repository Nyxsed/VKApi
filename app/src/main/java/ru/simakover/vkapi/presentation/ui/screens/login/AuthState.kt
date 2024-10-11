package ru.simakover.vkapi.presentation.ui.screens.login

sealed class AuthState {

    object Authorized : AuthState()
    object NotAuthorized : AuthState()

    object Initial: AuthState()
}