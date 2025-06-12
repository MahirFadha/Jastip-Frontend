package com.example.jastip.ui.screen.user.akun

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.model.User
import com.example.jastip.domain.usecase.EditUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AkunViewModel @Inject constructor(
    private val editUseCase: EditUseCase
) : ViewModel() {
    var name by mutableStateOf("")
    var nim by mutableStateOf("")
    var nomorHp by mutableStateOf("")
    var password by mutableStateOf("")
    var akunState by mutableStateOf<AkunState>(AkunState.Idle)
        private set

    fun setUser(user: User) {
        name = user.name
        password = user.password
        nim = user.nim
        nomorHp = user.nomorHp
    }


    fun edit() {
        viewModelScope.launch {
            akunState = AkunState.Loading
            try {
                val currentNim = nim
                val currentNomor = nomorHp
                val user = User(name = name, nim = currentNim, password = password, nomorHp = currentNomor)
                editUseCase(user)
                akunState = AkunState.Success("Edit berhasil!")
            } catch (e: Exception) {
                akunState = AkunState.Error(e.message ?: "Terjadi kesalahan")
            }
        }
    }
}