package ru.simakover.vkapi.presentation.screens.main

sealed class AuthState {

    object Authorized : AuthState()
    object NotAuthorized : AuthState()

    object Initial: AuthState()
}