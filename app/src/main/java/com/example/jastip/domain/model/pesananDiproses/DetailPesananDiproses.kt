package com.example.jastip.domain.model.pesananDiproses

data class DetailPesananDiproses(
    val imageUrl: String,
    val menuName: String,
    val sesi: String,
    val category: String,
    val jumlah: Int,
    val hargaItem: Double,
)
