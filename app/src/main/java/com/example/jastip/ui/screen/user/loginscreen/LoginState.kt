package com.example.jastip.ui.screen.user.loginscreen

import com.example.jastip.domain.model.User

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val error: String) : LoginState()
}