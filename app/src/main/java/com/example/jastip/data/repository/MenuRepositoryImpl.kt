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

    private fun readMenuCSV(): List<MenuEntity> {
        val menuList = mutableListOf<MenuEntity>()
        val inputStream = context.assets.open("menu_grabfood.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.readLine() // skip header jika ada
        reader.forEachLine { line ->
            val tokens = line.split(";") // ganti koma ke titik koma
            if (tokens.size >= 5) {
                try {
                    val priceClean = tokens[2].replace(".", "").trim()
                    val item = MenuEntity(
                        id = tokens[0].toInt(),
                        name = tokens[1],
                        price = priceClean.toInt(),
                        category = tokens[3],
                        imageUrl = tokens[4].trim()
                    )
                    menuList.add(item)
                } catch (e: Exception) {
                    e.printStackTrace() // debugging jika error parsing
                }
            }
        }
        return menuList
    }


    fun parseCsvToMenuEntities(csvText: String): List<MenuEntity> {
        val lines = csvText.lines()
        val result = mutableListOf<MenuEntity>()

        for ((index, line) in lines.withIndex()) {
            if (index == 0 || line.isBlank()) continue

            val tokens = line.split(";")
            if (tokens.size < 5) continue

            val id = tokens[0].toIntOrNull() ?: continue
            val name = tokens[1]
            val price = tokens[2].toIntOrNull() ?: 0
            val category = tokens[3]
            val imageUrl = tokens[4]

            result.add(MenuEntity(id, name, price, category, imageUrl))
        }

        return result
    }

    override suspend fun insertMenuItemsFromCSV() {
        val items = readMenuCSV()
        dao.insertAll(items)
    }

    override suspend fun getAllMenu(): List<MenuEntity> = dao.getAll()

    override suspend fun getMenusFromDb(): List<MenuEntity> {
        return dao.getAllMenus()
    }
}
