package com.example.jastip.data.local.entity

import androidx.room.PrimaryKey

data class DetailPesananEntity(
    @PrimaryKey (autoGenerate = true)val idDetailPesanan: Int = 0,
    val idPesanan: Int,
    val idMenu:Int,
    val jumlah: Int,
    val hargaItem: Double
)
