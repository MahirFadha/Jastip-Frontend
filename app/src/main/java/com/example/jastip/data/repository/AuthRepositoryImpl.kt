package com.example.jastip.data.repository

import com.example.jastip.data.local.TokenManager
import com.example.jastip.data.local.dao.UserDao
import com.example.jastip.domain.model.User
import com.example.jastip.domain.repository.IAuithRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val tokenManager: TokenManager
): IAuithRepository {

    override suspend fun login(nim: String, password: String): User? {
        val user = userDao.login(nim, password)
        return user?.let{
            val isiUser = User(it.name, it.nim, it.nomorHp, it.password, it.role)
            val fakeToken = "sanctum_token_${user.nim}"
            tokenManager.saveToken(fakeToken)
            tokenManager.saveRole(user.role)
            tokenManager.saveUser(isiUser)
            tokenManager.waktuLogin()
            return isiUser
        }
    }

    override fun logout() {
        tokenManager.clearToken()
    }

    override fun getToken(): String? = tokenManager.getToken()

    override fun getRole(): String? = tokenManager.getRole()
}