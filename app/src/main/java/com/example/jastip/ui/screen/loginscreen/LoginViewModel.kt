package com.example.jastip.ui.screen.loginscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var nim by mutableStateOf("")
    var password by mutableStateOf("")
    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
        private set

    fun login(nim: String, password: String) {
        viewModelScope.launch {
            loginState = LoginState.Loading
            try {
                val user = loginUseCase(nim, password)
                if (user != null) {
                    loginState = LoginState.Success(user)

                } else {
                    loginState = LoginState.Error("NIM atau Password salah")
                }
            } catch (e: Exception) {
                loginState = LoginState.Error(e.message ?: "Terjadi kesalahan")
            }
        }
    }

    fun resetState() {
        loginState = LoginState.Idle
    }
}