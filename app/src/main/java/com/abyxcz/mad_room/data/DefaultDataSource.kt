package com.abyxcz.mad_room.data

import kotlinx.coroutines.flow.Flow

/**
 * Main entry point for accessing tasks data.
 */
interface DefaultDataSource {

    suspend fun getItems(): Result<List<ItemEntity>>

    suspend fun getItem(taskId: String): Result<ItemEntity>

    suspend fun saveItem(task: ItemEntity)

    suspend fun updateItem(task: ItemEntity) : Int

    suspend fun deleteItem(task: ItemEntity)

    suspend fun deleteAllItems()
}