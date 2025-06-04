package com.example.jastip.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jastip.data.local.dao.KeranjangDao
import com.example.jastip.data.local.dao.MenuItemDao
import com.example.jastip.data.local.dao.PesananDao
import com.example.jastip.data.local.dao.UserDao
import com.example.jastip.data.local.entity.DetailPesananEntity
import com.example.jastip.data.local.entity.KeranjangEntity
import com.example.jastip.data.local.entity.MenuEntity
import com.example.jastip.data.local.entity.PesananEntity
import com.example.jastip.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        MenuEntity::class,
        KeranjangEntity::class,
        PesananEntity::class,
        DetailPesananEntity::class],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun MenuItemDao(): MenuItemDao
    abstract fun KeranjangDao(): KeranjangDao
    abstract fun PesananDao(): PesananDao
}

