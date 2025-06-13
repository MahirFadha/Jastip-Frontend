package com.example.jastip.data.repository

import com.example.jastip.data.local.dao.PesananDao
import com.example.jastip.data.local.entity.DetailPesananEntity
import com.example.jastip.data.local.entity.PesananEntity
import com.example.jastip.domain.model.Keranjang
import com.example.jastip.domain.model.riwayatPesanan.RiwayatPesanan
import com.example.jastip.domain.repository.IPesananRepository

class PesananRepositoryImpl (private val pesananDao: PesananDao): IPesananRepository {
    override suspend fun placeOrder(nim: String, items: List<Keranjang>): Result<Unit> {
        return try {
            val total = items.sumOf { it.price * it.quantity }
            val pesanan = PesananEntity(userNim = nim, totalHarga = total)
            val detailPesanan = items.map {
                DetailPesananEntity(idPesanan = 0, idMenu = it.menuId, jumlah = it.quantity, hargaItem = it.price, sesi = it.sesi)
            }
            pesananDao.insertPesananWithDetail(pesanan, detailPesanan)
            Result.success(Unit)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getPesanan(nim: String): List<RiwayatPesanan> {
        return pesananDao.getPesanan(nim)
    }

    override suspend fun batalkanPesanan(idPesanan: Int) {
        pesananDao.batalkanPesanan(idPesanan)
    }
}