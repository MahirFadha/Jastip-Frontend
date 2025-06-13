package com.example.jastip.domain.model.riwayatPesanan

data class RiwayatPesanan(
    val idPesanan: String,
    val status: String,
    val waktuPesanan: String,
    val jumlah: Int,
    val hargaItem: Double,
    val sesi: String,
    val name: String,
    val imageUrl: String
)