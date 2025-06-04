package com.example.jastip.data.local.dao

import androidx.room.Insert
import androidx.room.Transaction
import com.example.jastip.data.local.entity.DetailPesananEntity
import com.example.jastip.data.local.entity.PesananEntity

interface PesananDao {
    @Insert
    suspend fun insertPesanan(pesananEntity: PesananEntity): Long

    @Insert
    suspend fun insertDetailPesanan(detailPesananEntity: List<DetailPesananEntity>)

    @Transaction
    suspend fun insertPesananWithDetail(pesananEntity: PesananEntity, detailPesananEntity: List<DetailPesananEntity>) {
        val pesananId = insertPesanan(pesananEntity)
        val updateDetail = detailPesananEntity.map { it.copy(idPesanan = pesananId.toInt()) }
        insertDetailPesanan(updateDetail)
    }
}
