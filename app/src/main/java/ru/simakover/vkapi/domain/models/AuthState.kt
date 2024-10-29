package ru.simakover.vkapi.domain.models

sealed class AuthState {

    object Authorized : AuthState()
    object NotAuthorized : AuthState()

    object Initial: AuthState()
}