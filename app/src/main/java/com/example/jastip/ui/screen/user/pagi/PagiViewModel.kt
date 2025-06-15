package com.example.jastip.ui.screen.user.pagi

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.usecase.user.MenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import javax.inject.Inject


@HiltViewModel
class PagiViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PagiState())
    val state: State<PagiState> = _state

    init {
        // ⏬ Load data langsung saat ViewModel dibuat
        insertAndLoadMenus()
    }

    private fun insertAndLoadMenus() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                // ⏬ Pastikan data dari CSV dimasukkan dulu
//                menuUseCase.insertMenuItemsFromCSV()

                // ⏬ Kemudian ambil semua data dari DB
                val menus = menuUseCase.getMenusFromDb()

                _state.value = PagiState(menuList = menus, isLoading = false)
            } catch (e: Exception) {
                _state.value = PagiState(
                    errorMessage = e.message ?: "Terjadi kesalahan",
                    isLoading = false
                )
            }
        }
    }

    // Jika ingin dipanggil ulang manual dari UI
    fun reload() {
        insertAndLoadMenus()
    }
}