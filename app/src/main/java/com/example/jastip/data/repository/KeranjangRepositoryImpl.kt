package com.example.jastip.data.repository

import com.example.jastip.data.local.dao.KeranjangDao
import com.example.jastip.data.local.entity.KeranjangEntity
import com.example.jastip.domain.model.Keranjang
import com.example.jastip.domain.repository.IKeranjangRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class KeranjangRepositoryImpl(private val dao:  KeranjangDao) : IKeranjangRepository {
    override suspend fun addToKeranjang(item: Keranjang) {
        val entity = KeranjangEntity(
            id = item.id,
            menuId = item.menuId,
            name = item.name,
            price = item.price,
            quantity = item.quantity,
            imageUrl = item.imageUrl
        )
        dao.insert(entity)
    }

    override fun getKeranjangItems(): Flow<List<Keranjang>> {
        return dao.getAll().map { list ->
            list.map {
                Keranjang(
                    id = it.id,
                    menuId = it.menuId,
                    name = it.name,
                    price = it.price,
                    quantity = it.quantity,
                    imageUrl = it.imageUrl
                )
            }
        }
    }

    override suspend fun removeItem(id: Int) = dao.deleteById(id)
    override suspend fun clearKeranjang() = dao.clearAll()
    override suspend fun updateQuantity(itemId: Int, quantity: Int) {
        dao.updateQuantity(itemId, quantity)
    }
}
