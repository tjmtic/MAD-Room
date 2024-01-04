package com.abyxcz.mad_room.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(users: List<ItemEntity>)

    @Query("SELECT * FROM items")
    fun getAllUsers(): List<ItemEntity>

    @Query("SELECT * FROM items WHERE itemId = :userId")
    fun getUser(userId: Int): Flow<ItemEntity>

    @Query("DELETE FROM items")
    suspend fun deleteAllUsers()

}