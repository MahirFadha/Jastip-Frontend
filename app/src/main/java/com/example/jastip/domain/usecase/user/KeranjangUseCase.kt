package com.example.jastip.domain.usecase.user

import com.example.jastip.domain.model.Keranjang
import com.example.jastip.domain.repository.IKeranjangRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KeranjangUseCase @Inject constructor
    (private val repository: IKeranjangRepository) {

    suspend fun addItem(item: Keranjang) {
        repository.addToKeranjang(item)
    }

//    fun getAllItems(): Flow<List<Keranjang>> {
//        return repository.getKeranjangItems()
//    }

    suspend fun updateQuantity(itemId: Int, newQuantity: Int) {
        repository.updateQuantity(itemId, newQuantity)
    }

    suspend fun deleteItem(item: Keranjang) {
        repository.removeItem(item.id)
    }

    suspend fun clearCart(nim: String) {
        repository.clearKeranjang()
    }

    suspend fun getKeranjangByUserNim(nim: String): Flow<List<Keranjang>> {
        return repository.getKeranjangItemsByUserNim(nim)
    }
}