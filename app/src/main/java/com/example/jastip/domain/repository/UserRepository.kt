package com.example.jastip.domain.repository

import com.example.jastip.domain.model.User

interface UserRepository {
    suspend fun register(user: User)
    suspend fun login(nim: Long, password: String): User?
    suspend fun edit(user: User)
}
