package com.example.jastip.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.jastip.data.local.entity.DetailPesananEntity
import com.example.jastip.data.local.entity.PesananEntity
import com.example.jastip.domain.model.pesananDiproses.GrupPesananDiproses
import com.example.jastip.domain.model.pesananDiproses.PesananDiproses
import com.example.jastip.domain.model.riwayatPesanan.RiwayatPesanan

@Dao
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

    @Query("SELECT p.idPesanan, p.status, p.waktuPesanan, dp.jumlah, dp.hargaItem, dp.sesi, m.menuName, m.imageUrl" +
            " FROM pesanan as p"+
            " LEFT JOIN detailPesanan as dp ON p.idPesanan = dp.idPesanan"+
            " LEFT JOIN menu as m ON dp.idMenu = m.id "+
            "WHERE p.userNim = :nim " +
            "ORDER BY p.waktuPesanan DESC")
    suspend fun getPesanan(nim: String): List<RiwayatPesanan>

    @Query("UPDATE pesanan SET status = 'Dibatalkan' WHERE idPesanan = :idPesanan")
    suspend fun batalkanPesanan(idPesanan: Int)

    @Query("SELECT p.idPesanan, u.name, p.waktuPesanan, p.status, dp.jumlah, dp.hargaItem, dp.sesi, m.menuName, m.imageUrl, m.category, p.totalHarga" +
            " FROM pesanan as p"+
            " LEFT JOIN detailPesanan as dp ON p.idPesanan = dp.idPesanan"+
            " LEFT JOIN menu as m ON dp.idMenu = m.id "+
            "LEFT JOIN users as u ON p.userNim = u.nim " +
            "WHERE p.status IN ('Selesai','Diproses') " +
            "ORDER BY p.waktuPesanan DESC")
    suspend fun getPesananProses(): List<PesananDiproses>

//    @Query("SELECT p.idPesanan, p.status, p.waktuPesanan, dp.jumlah, dp.hargaItem, dp.sesi, m.name, m.imageUrl" +
//            " FROM pesanan as p"+
//            " LEFT JOIN detailPesanan as dp ON p.idPesanan = dp.idPesanan"+
//            " LEFT JOIN menu as m ON dp.idMenu = m.id "+
//            "WHERE p.status IN ('Selesai', 'Dibatalkan') AND p.userNim = :nim " +
//            "ORDER BY p.waktuPesanan DESC")
//    suspend fun getPesananSelesai(nim: String)
}
