package com.example.jastip.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jastip.data.local.dao.MenuItemDao
import com.example.jastip.data.local.dao.UserDao
import com.example.jastip.data.local.entity.MenuEntity
import com.example.jastip.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, MenuEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun menuItemDao(): MenuItemDao
}

