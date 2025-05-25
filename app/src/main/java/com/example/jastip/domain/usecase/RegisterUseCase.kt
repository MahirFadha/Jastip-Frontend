package com.example.jastip.domain.usecase

import com.example.jastip.domain.model.User
import com.example.jastip.domain.repository.UserRepository

class RegisterUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) = repository.register(user)
}
