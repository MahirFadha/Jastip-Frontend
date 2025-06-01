package com.example.jastip.domain.usecase

import com.example.jastip.domain.model.User
import com.example.jastip.domain.repository.UserRepository
import com.example.jastip.utils.SecurityUser

class LoginUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(nim: String, password: String): User? {
        val hashedPassword = SecurityUser().hashPassword(password)
        return repository.login(nim, hashedPassword)
    }
}
