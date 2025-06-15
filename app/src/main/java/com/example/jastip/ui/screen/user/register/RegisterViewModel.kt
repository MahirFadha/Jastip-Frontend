package com.example.jastip.ui.screen.user.register

import com.example.jastip.domain.usecase.user.RegisterUseCase
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var name by mutableStateOf("")
    var nim by mutableStateOf("")
    var password by mutableStateOf("")
    var nomorHp by mutableStateOf("")
    var role by mutableStateOf("")
    var registerState by mutableStateOf<RegisterState>(RegisterState.Idle)
        private set

    fun register() {
        viewModelScope.launch {
            registerState = RegisterState.Loading
            try {
                if (nim.isEmpty()) {
                    registerState = RegisterState.Error("NIM tidak valid")
                    return@launch
                }
                registerUseCase(User(name, nim, nomorHp, password, role))
                registerState = RegisterState.Success("Registrasi berhasil!")
            } catch (e: Exception) {
                registerState = RegisterState.Error("Registrasi gagal: ${e.message}")
            }
        }
    }
}
