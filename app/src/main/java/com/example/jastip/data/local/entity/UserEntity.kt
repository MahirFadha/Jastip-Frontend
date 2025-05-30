package com.example.jastip.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val nim: Long,
    val name: String,
    val nomorHp: Long,
    val password: String
)
