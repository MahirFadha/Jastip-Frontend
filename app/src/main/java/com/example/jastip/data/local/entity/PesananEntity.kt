package com.example.jastip.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pesanan")
data class PesananEntity(
    @PrimaryKey (autoGenerate = true)val idPesanan: Int = 0,
    val userNim: String,
    val totalHarga: Double,
    val waktuPesanan: Long = System.currentTimeMillis(),
    val status: String = "Diproses",
    )
