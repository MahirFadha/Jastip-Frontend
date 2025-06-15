package com.example.jastip.ui.screen.admin.menu

import com.example.jastip.data.local.entity.MenuEntity

data class MenuState (
    val isLoading: Boolean = false,
    val menuList: List<MenuEntity> = emptyList()
)