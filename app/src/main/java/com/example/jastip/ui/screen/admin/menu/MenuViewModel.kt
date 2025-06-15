package com.example.jastip.ui.screen.admin.menu

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jastip.domain.usecase.admin.MenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
): ViewModel(){

    private val stateKhusus = mutableStateOf(MenuState())
    val state: State<MenuState> = stateKhusus

    init {
        loadMenu()
    }

    private fun loadMenu(){
        viewModelScope.launch {
            stateKhusus.value = stateKhusus.value.copy(isLoading = true)
            try {
                val menus = menuUseCase.getMenusFromDb()
                stateKhusus.value = stateKhusus.value.copy(menuList = menus, isLoading = false)
            } catch (e: Exception) {
                print(e.printStackTrace())
            }
        }
    }
}