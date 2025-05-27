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

    @Query("SELECT * FROM menu")
    suspend fun getAll(): List<MenuEntity>

}

