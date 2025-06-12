package com.example.jastip.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keranjang")
data class KeranjangEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val menuId: Int,
    val userNim: String,
    val sesi: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String
)
