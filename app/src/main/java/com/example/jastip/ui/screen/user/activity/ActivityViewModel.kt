package com.example.jastip.ui.screen.user.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jastip.domain.model.riwayatPesanan.GrupRiwayatPesanan
import com.example.jastip.domain.model.riwayatPesanan.PesananRiwayatPesanan
import com.example.jastip.domain.model.riwayatPesanan.RiwayatPesanan
import com.example.jastip.domain.usecase.RiwayatPesananUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val riwayatPesananUseCase: RiwayatPesananUseCase
) : ViewModel() {

    private val riwayat = mutableStateOf<List<GrupRiwayatPesanan>>(emptyList())
    val riwayatPesanan: State<List<GrupRiwayatPesanan>> = riwayat

    fun loadPesanan(nim: String) {
        viewModelScope.launch {
            val hasil: List<RiwayatPesanan> = riwayatPesananUseCase(nim)
            riwayat.value = convertGrup(hasil)
        }
    }
    fun convertGrup(data: List<RiwayatPesanan>): List<GrupRiwayatPesanan>{
        return data.groupBy { it.idPesanan }.map { (idPesanan, pesanan) ->
            val pertama = pesanan.first()
            GrupRiwayatPesanan(
                idPesanan = idPesanan,
                status = pertama.status,
                waktuPesanan = pertama.waktuPesanan,
                pesanan = pesanan.map {
                    PesananRiwayatPesanan(
                        name = it.name,
                        sesi = it.sesi,
                        hargaItem = it.hargaItem,
                        jumlah = it.jumlah,
                        imageUrl = it.imageUrl
                    )
                }
            )
        }
    }
}