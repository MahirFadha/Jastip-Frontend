package com.example.jastip.ui.screen.register

import com.example.jastip.domain.model.User
import com.example.jastip.ui.screen.loginscreen.LoginState

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val succes: String) : RegisterState()
    data class Error(val error: String) : RegisterState()
}