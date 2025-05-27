package com.example.jastip.ui.screen.register

import com.example.jastip.domain.usecase.RegisterUseCase
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.model.User
import com.example.jastip.utils.SecurityUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var name by mutableStateOf("")
    var nimInput by mutableStateOf("")
    val nim: Long? get() = nimInput.toLongOrNull()
    var password by mutableStateOf("")
    var nomorHpInput by mutableStateOf("")
    val nomorHp: Long? get() = nomorHpInput.toLongOrNull()
    var registerState by mutableStateOf<RegisterState>(RegisterState.Idle)
        private set

    fun register() {
        viewModelScope.launch {
            registerState = RegisterState.Loading
            try {
                if (nim == null) {
                    registerState = RegisterState.Error("NIM tidak valid")
                    return@launch
                }

                registerUseCase(User(name, nim!!, nomorHp!!, password))
                registerState = RegisterState.Success("Registrasi berhasil!")
            } catch (e: Exception) {
                registerState = RegisterState.Error("Registrasi gagal: ${e.message}")
            }
        }
    }
}
