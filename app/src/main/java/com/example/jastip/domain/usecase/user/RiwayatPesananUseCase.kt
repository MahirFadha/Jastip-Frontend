package com.example.jastip.domain.usecase.user

import com.example.jastip.domain.model.riwayatPesanan.RiwayatPesanan
import com.example.jastip.domain.repository.IPesananRepository
import javax.inject.Inject

class RiwayatPesananUseCase @Inject constructor(
    private val repository: IPesananRepository
){
    suspend operator fun invoke(nim: String): List<RiwayatPesanan>{
        return repository.getPesanan(nim)
    }
}
