package com.example.jastip.domain.repository

import com.example.jastip.domain.model.Keranjang
import com.example.jastip.domain.model.pesananDiproses.GrupPesananDiproses
import com.example.jastip.domain.model.pesananDiproses.PesananDiproses
import com.example.jastip.domain.model.riwayatPesanan.RiwayatPesanan

interface IPesananRepository {
    suspend fun placeOrder(nim: String, items: List<Keranjang>): Result<Unit>
    suspend fun getPesanan(nim: String): List<RiwayatPesanan>
    suspend fun batalkanPesanan(idPesanan: Int)
    suspend fun getPesananProses(): List<GrupPesananDiproses>
}