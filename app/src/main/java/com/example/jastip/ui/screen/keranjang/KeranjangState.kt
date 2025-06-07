package com.example.jastip.ui.screen.keranjang

import com.example.jastip.domain.model.Keranjang

data class KeranjangState(
    val items: List<Keranjang> = emptyList(),
    val isLoading: Boolean = false,
    val isOrdering: Boolean = false,
    val error: String? = null,
    val selectedItems: Set<Int> = emptySet(),
)