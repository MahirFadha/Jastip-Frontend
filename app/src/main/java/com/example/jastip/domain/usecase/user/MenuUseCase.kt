package com.example.jastip.domain.usecase.user

import com.example.jastip.data.local.entity.MenuEntity
import com.example.jastip.domain.repository.IMenuRepository
import javax.inject.Inject

class MenuUseCase @Inject constructor(
    private val repository: IMenuRepository
) {
    suspend fun insertMenuItemsFromCSV() {
        repository.insertMenuItemsFromCSV()
    }

    suspend fun getMenusFromDb(): List<MenuEntity> {
        return repository.getMenusFromDb()
    }
}





