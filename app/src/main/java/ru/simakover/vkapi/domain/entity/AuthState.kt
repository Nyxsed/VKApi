package ru.simakover.vkapi.domain.entity

sealed class AuthState {

    object Authorized : AuthState()
    object NotAuthorized : AuthState()

    object Initial: AuthState()
}