package com.example.jastip.domain.model

data class Keranjang(
    val id: Int = 0,
    val userNim: String,
    val menuId: Int,
    val sesi: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String
)
