package com.example.jastip.domain.model.pesananDiproses

data class GrupPesananDiproses (
    val nama:String,
    val status: String,
    val idPesanan: String,
    val detail: List<DetailPesananDiproses>,
    val totalHarga: Double
)