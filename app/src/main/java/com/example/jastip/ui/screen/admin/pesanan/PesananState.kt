package com.example.jastip.ui.screen.admin.pesanan

import com.example.jastip.domain.model.pesananDiproses.GrupPesananDiproses

data class PesananState (
    val isLoading: Boolean = false,
    val pesananList: List<GrupPesananDiproses> = emptyList()
)