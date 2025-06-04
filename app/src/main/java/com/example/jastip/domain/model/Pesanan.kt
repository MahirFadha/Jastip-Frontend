package com.example.jastip.domain.model

data class Pesanan(
    val idPesanan: Int = 0,
    val nim: String,
    val totalHarga: Double,
    val waktuPesanan: Long = System.currentTimeMillis(),
    val status: String,
    val sesi: String
)
