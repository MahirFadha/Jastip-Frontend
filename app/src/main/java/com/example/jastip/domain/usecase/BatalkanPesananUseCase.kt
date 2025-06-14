package com.example.jastip.domain.usecase

import com.example.jastip.domain.repository.IPesananRepository
import javax.inject.Inject

class BatalkanPesananUseCase @Inject constructor(
    private val repository: IPesananRepository
) {
    suspend operator fun invoke(idPesanan: Int) {
        repository.batalkanPesanan(idPesanan)
    }
}