package com.example.jastip.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val type: String, //tambah kolom
    val imageUrl: String
)



