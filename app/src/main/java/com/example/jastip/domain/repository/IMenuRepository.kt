package com.example.jastip.domain.repository

import com.example.jastip.data.local.entity.MenuEntity

interface IMenuRepository {
    suspend fun insertMenuItemsFromCSV()
    suspend fun getAllMenu(): List<MenuEntity>
    suspend fun getMenusFromDb(): List<MenuEntity>
    //suspend fun replaceMenuData(menus: List<MenuEntity>)
}