package com.example.jastip.domain.usecase

import com.example.jastip.domain.model.User
import com.example.jastip.domain.repository.UserRepository
import com.example.jastip.utils.SecurityUser

class RegisterUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) {
        val hashedPassword = SecurityUser().hashPassword(user.password)
        val userWithHashedPassword = user.copy(password = hashedPassword)
        repository.register(userWithHashedPassword)
    }
}
