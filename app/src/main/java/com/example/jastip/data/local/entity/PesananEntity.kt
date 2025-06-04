package com.example.jastip.data.local.entity

import androidx.room.PrimaryKey

data class PesananEntity(
    @PrimaryKey (autoGenerate = true)val idPesanan: Int = 0,
    val nim: String,
    val totalHarga: Double,
    val waktuPesanan: Long = System.currentTimeMillis(),
    val status: String,
    val waktu: String
    )
