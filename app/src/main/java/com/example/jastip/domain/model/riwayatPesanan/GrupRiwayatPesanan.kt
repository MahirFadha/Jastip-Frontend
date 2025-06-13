package com.example.jastip.domain.model.riwayatPesanan

data class GrupRiwayatPesanan(
    val idPesanan: Int,
    val status: String,
    val waktuPesanan: String,
    val pesanan: List<PesananRiwayatPesanan>
)
