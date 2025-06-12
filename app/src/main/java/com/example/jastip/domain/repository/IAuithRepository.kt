package com.example.jastip.domain.repository

import com.example.jastip.domain.model.User

interface IAuithRepository {
    suspend fun login(nim: String, password: String): User?
    fun logout()
    fun getToken(): String?
    fun getRole(): String?
}