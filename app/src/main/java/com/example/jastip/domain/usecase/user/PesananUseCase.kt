package com.example.jastip.domain.usecase.user

import com.example.jastip.domain.model.Keranjang
import com.example.jastip.domain.repository.IPesananRepository
import javax.inject.Inject

class PesananUseCase @Inject constructor (private val repository: IPesananRepository) {
    suspend fun placeOrder(nim: String, items: List<Keranjang>) = repository.placeOrder(nim, items)
}