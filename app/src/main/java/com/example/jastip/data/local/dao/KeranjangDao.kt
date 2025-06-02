package com.example.jastip.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jastip.data.local.entity.KeranjangEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KeranjangDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: KeranjangEntity)

    @Query("SELECT * FROM keranjang WHERE userNim = :nim")
    fun getByUserNim(nim: String): Flow<List<KeranjangEntity>>

    @Query("DELETE FROM keranjang WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM keranjang")
    suspend fun clearAll()

    @Query("UPDATE keranjang SET quantity = :quantity WHERE id = :itemId")
    suspend fun updateQuantity(itemId: Int, quantity: Int)

}
