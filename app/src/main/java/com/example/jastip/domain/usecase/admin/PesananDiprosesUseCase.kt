package com.example.jastip.domain.usecase.admin

import com.example.jastip.domain.model.riwayatPesanan.RiwayatPesanan
import com.example.jastip.domain.repository.IPesananRepository
import javax.inject.Inject

class PesananDiprosesUseCase @Inject constructor(private val pesananRepository: IPesananRepository) {
    suspend operator fun invoke(): List<RiwayatPesanan> {
        return pesananRepository.getPesananProses()
    }
}