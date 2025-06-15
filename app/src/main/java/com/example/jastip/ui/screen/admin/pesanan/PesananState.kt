package com.example.jastip.ui.screen.admin.pesanan

import com.example.jastip.domain.model.riwayatPesanan.RiwayatPesanan

data class PesananState (
    val isLoading: Boolean = false,
    val pesananList: List<RiwayatPesanan> = emptyList()
)