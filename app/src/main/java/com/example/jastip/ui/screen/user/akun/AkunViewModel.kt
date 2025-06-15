package com.example.jastip.ui.screen.user.akun

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.data.local.TokenManager
import com.example.jastip.domain.model.User
import com.example.jastip.domain.usecase.user.EditUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AkunViewModel @Inject constructor(
    private val editUseCase: EditUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val tokenManager = TokenManager(context)
    var user by mutableStateOf<User?>(null)
    var name by mutableStateOf("")
    var nim by mutableStateOf("")
    var nomorHp by mutableStateOf("")
    var password by mutableStateOf("")
    var akunState by mutableStateOf<AkunState>(AkunState.Idle)
        private set
    init {
        // Load user saat ViewModel dibuat
        user = tokenManager.getUser()
    }

    fun aturUser(dataUser: User) {
        user = dataUser
        name = dataUser.name
        nim = dataUser.nim
        nomorHp = dataUser.nomorHp
        password = ""    }

    fun edit() {
        viewModelScope.launch {
            akunState = AkunState.Loading
            try {
                user?.let {
                    val updatedUser = it.copy(
                        name = name,
                        nim = nim,
                        nomorHp = nomorHp,
                        password = if (password.isBlank()) it.password else password
                    )
                    editUseCase(updatedUser)
                    tokenManager.saveUser(it)
                }
                akunState = AkunState.Success("Edit berhasil!")
            } catch (e: Exception) {
                akunState = AkunState.Error(e.message ?: "Terjadi kesalahan")
            }
        }
    }
}