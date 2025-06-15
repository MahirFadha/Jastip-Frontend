package com.example.jastip.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.content.edit
import com.example.jastip.domain.model.User


class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val pref = EncryptedSharedPreferences.create(
        context,
        "user_pref",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: String){
        pref.edit { putString("token", token) }
    }

    fun getToken(): String? = pref.getString("token", null)

    fun clearToken(){
        pref.edit { clear() }
    }

    fun saveRole(role: String){
        pref.edit { putString("role", role) }
    }

    fun getRole(): String? = pref.getString("role", null)

    fun saveUser(user: User){
        pref.edit{
            putString("name", user.name)
            putString("nim", user.nim)
            putString("nomorHp", user.nomorHp)
            putString("password", user.password)
            putString("role", user.role)
        }
    }

    fun getUser(): User?{
        val nim = pref.getString("nim", null) ?: return null
        val name = pref.getString("name", "") ?: ""
        val nomorHp = pref.getString("nomorHp", "") ?: ""
        val role = pref.getString("role", "") ?: ""
        val password = pref.getString("password", "") ?: ""

        return User(
            name = name,
            nim = nim,
            nomorHp = nomorHp,
            password = password,
            role = role
        )
    }

    fun waktuLogin(){
        val sekarang = System.currentTimeMillis()
        pref.edit { putLong("waktuLogin", sekarang) }
    }

    fun cekLogin(kadaluarsa: Long = 3*24*60*60*1000): Boolean{
        val terakhirLogin = pref.getLong("waktuLogin", 0L)
        val sekarang = System.currentTimeMillis()
        return (sekarang - terakhirLogin) < kadaluarsa
    }
}