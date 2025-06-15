package com.example.jastip.domain.model.pesananDiproses

data class PesananDiproses(
    val name:String,
    val status: String,
    val idPesanan: String,
    val imageUrl: String,
    val menuName: String,
    val sesi: String,
    val category: String,
    val jumlah: Int,
    val hargaItem: Double,
    val totalHarga: Double
 )
