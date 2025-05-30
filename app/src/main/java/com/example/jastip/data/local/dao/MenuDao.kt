package com.example.jastip.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jastip.data.local.entity.MenuEntity

@Dao
interface MenuItemDao {

    @Query("SELECT * FROM menu")
    suspend fun getAllMenus(): List<MenuEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(menus: List<MenuEntity>)

    // ✅ Ambil menu berdasarkan tipe
    @Query("SELECT * FROM menu WHERE type = :type")
    suspend fun getMenusByType(type: String): List<MenuEntity>

    // ✅ Update kolom type berdasarkan id menu
    @Query("UPDATE menu SET type = :newType WHERE id = :menuId")
    suspend fun updateMenuType(menuId: Int, newType: String)
}
