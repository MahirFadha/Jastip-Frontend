package com.example.jastip.domain.model

data class DetailPesanan(
    val idDetailPesanan: Int,
    val idPesanan: Int,
    val idMenu: Int,
    val jumlah: Int,
    val hargaItem: Double
)
