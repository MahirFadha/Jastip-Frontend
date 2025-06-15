package com.example.jastip.domain.model.riwayatPesanan

data class RiwayatPesanan(
    val idPesanan: Int,
    val status: String,
    val waktuPesanan: String,
    val jumlah: Int,
    val hargaItem: Double,
    val sesi: String,
    val menu: String,
    val imageUrl: String
)