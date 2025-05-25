package com.example.jastip.domain.usecase

import com.example.jastip.domain.model.User
import com.example.jastip.domain.repository.UserRepository

class LoginUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(nim: String, password: String): User? =
        repository.login(nim, password)
}
