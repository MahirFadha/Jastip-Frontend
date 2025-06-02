package com.example.jastip.domain.repository

import com.example.jastip.domain.model.Keranjang
import kotlinx.coroutines.flow.Flow

interface IKeranjangRepository {
    suspend fun addToKeranjang(item: Keranjang)
//    fun getKeranjangItems(): Flow<List<Keranjang>>
    suspend fun removeItem(id: Int)
    suspend fun clearKeranjang()
    suspend fun updateQuantity(itemId: Int, quantity: Int)
    suspend fun getKeranjangItemsByUserNim(nim: String): Flow<List<Keranjang>>
}
