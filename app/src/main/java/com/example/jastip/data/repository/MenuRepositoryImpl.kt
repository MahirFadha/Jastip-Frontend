package com.example.jastip.data.repository

import android.content.Context
import com.example.jastip.data.local.dao.MenuItemDao
import com.example.jastip.data.local.entity.MenuEntity
import com.example.jastip.domain.repository.IMenuRepository
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val dao: MenuItemDao,
    private val context: Context
) : IMenuRepository {

    private fun readMenuFromAssets(): List<MenuEntity> {
        val menuList = mutableListOf<MenuEntity>()

        try {
            val inputStream = context.assets.open("menu_kfc.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.readLine() // Skip header jika ada

            reader.forEachLine { line ->
                val tokens = line.split(";")
                if (tokens.size >= 5) {
                    try {
                        val id = tokens[0].toInt()
                        val name = tokens[1].trim()
                        val price = tokens[2].replace(".", "").trim().toIntOrNull() ?: 0
                        val category = tokens[3].trim()
                        val type = tokens[4].trim()
                        val imageUrl = tokens[5].trim()

                        val menuItem = MenuEntity(
                            id = id,
                            name = name,
                            price = price,
                            category = category,
                            type = type,
                            imageUrl = imageUrl
                        )
                        menuList.add(menuItem)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return menuList
    }

    override suspend fun insertMenuItemsFromCSV() {
        val items = readMenuFromAssets()
        dao.insertAll(items)  // Hanya insert, tanpa hapus data lama
    }

    override suspend fun getAllMenu(): List<MenuEntity> {
        return dao.getAllMenus()
    }

    override suspend fun getMenusFromDb(): List<MenuEntity> {
        return dao.getAllMenus()
    }

//    override suspend fun replaceMenuData(menus: List<MenuEntity>) {
//        dao.insertAll(menus)  // Hanya insert data baru, tanpa hapus dulu
//    }
}
