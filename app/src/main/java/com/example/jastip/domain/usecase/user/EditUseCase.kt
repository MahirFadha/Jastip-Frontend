package com.example.jastip.domain.usecase.user

import com.example.jastip.domain.model.User
import com.example.jastip.domain.repository.UserRepository
import com.example.jastip.utils.SecurityUser

class EditUseCase (private val repository: UserRepository) {
    suspend operator fun invoke(user: User) {
        val hashedPassword = SecurityUser().hashPassword(user.password)
        val userWithHashedPassword = user.copy(password = hashedPassword)
        repository.edit(userWithHashedPassword)
    }
}
