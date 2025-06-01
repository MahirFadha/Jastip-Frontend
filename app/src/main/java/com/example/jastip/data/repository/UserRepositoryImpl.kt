package com.example.jastip.data.repository

import com.example.jastip.data.local.dao.UserDao
import com.example.jastip.data.local.entity.UserEntity
import com.example.jastip.domain.model.User
import com.example.jastip.domain.repository.UserRepository

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {
    override suspend fun register(user: User): Unit =
        dao.registerUser(UserEntity(name = user.name, nim = user.nim, nomorHp = user.nomorHp, password = user.password))

    override suspend fun login(nim: String, password: String): User? {
        val entity = dao.login(nim, password)
        return entity?.let { User(it.name,it.nim, it.nomorHp, it.password) }
    }

    override suspend fun edit(user: User): Unit =
        dao.updateUser(UserEntity(name = user.name, nim = user.nim, nomorHp = user.nomorHp, password = user.password))
}
