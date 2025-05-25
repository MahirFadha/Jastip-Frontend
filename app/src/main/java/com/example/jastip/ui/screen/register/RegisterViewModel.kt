package com.example.jastip.ui.screen.register

import com.example.jastip.domain.usecase.RegisterUseCase
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
    var isLoading by mutableStateOf(false)
    var message by mutableStateOf<String?>(null)

    fun register() {
        if (name.isBlank() || nim.isBlank() || password.isBlank()) {
            message = "Semua field harus diisi"
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                registerUseCase(User(name, nim, password))
                message = "Registrasi berhasil!"
            } catch (e: Exception) {
                message = "Registrasi gagal: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
