package com.example.jastip.domain.repository

import com.example.jastip.data.local.entity.KeranjangEntity
import com.example.jastip.domain.model.Keranjang

interface IPesananRepository {
    suspend fun placeOrder(nim: String, items: List<Keranjang>): Result<Unit>
}