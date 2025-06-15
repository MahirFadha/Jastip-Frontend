package com.example.jastip.domain.usecase.user

import com.example.jastip.domain.model.User
import com.example.jastip.domain.repository.IAuithRepository
import com.example.jastip.utils.SecurityUser
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: IAuithRepository
) {
    suspend operator fun invoke(nim: String, password: String): User? {
        val hashedPassword = SecurityUser().hashPassword(password)
        return repository.login(nim, hashedPassword)
    }
}
