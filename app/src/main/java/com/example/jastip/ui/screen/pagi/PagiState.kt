package com.example.jastip.ui.screen.pagi

import com.example.jastip.data.local.entity.MenuEntity


data class PagiState(
    val isLoading: Boolean = false,
    val menuList: List<MenuEntity> = emptyList(),
    val errorMessage: String? = null
)