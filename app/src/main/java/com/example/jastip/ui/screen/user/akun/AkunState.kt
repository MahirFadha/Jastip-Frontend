package com.example.jastip.ui.screen.user.akun

sealed class AkunState {
    object Idle : AkunState()
    object Loading : AkunState()
    data class Success(val message: String) : AkunState()
    data class Error(val error: String) : AkunState()

}