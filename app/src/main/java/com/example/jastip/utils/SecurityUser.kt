package com.example.jastip.utils

import java.security.MessageDigest

class SecurityUser {
    fun hashPassword(password: String): String{
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}