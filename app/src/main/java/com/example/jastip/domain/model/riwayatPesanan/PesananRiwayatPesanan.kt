package com.example.jastip.domain.model.riwayatPesanan

data class PesananRiwayatPesanan(
    val menu: String,
    val sesi: String,
    val jumlah: Int,
    val hargaItem: Double,
    val imageUrl: String
)
