package com.abyxcz.mad_room.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Concrete implementation of a data source as a db.
 */
class ItemsLocalDataSource internal constructor(
    private val itemsDao: ItemDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DefaultDataSource {

    override fun observeItems(): Flow<Result<List<ItemEntity>>> {
        return itemsDao.observeItems().map {
            Result.Success(it)
        }
    }

    override fun observeItem(ItemId: String): Flow<Result<ItemEntity>> {
        return itemsDao.observeItemById(ItemId).map {
            Result.Success(it)
        }
    }

    override suspend fun refreshItem(ItemId: String) {
        //NO-OP
    }

    override suspend fun refreshItems() {
        //NO-OP
    }

    override suspend fun getItems(): Result<List<ItemEntity>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(itemsDao.getItems())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getItem(itemId: String): Result<ItemEntity> = withContext(ioDispatcher) {
        try {
            val item = itemsDao.getItemById(itemId)
            if (item != null) {
                return@withContext Result.Success(item)
            } else {
                return@withContext Result.Error(Exception("Item not found!"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveItem(item: ItemEntity) = withContext(ioDispatcher) {
        itemsDao.insertItem(item)
    }

    override suspend fun saveItems(items: List<ItemEntity>) = withContext(ioDispatcher) {
        itemsDao.insertItems(items)
    }

    override suspend fun updateItem(item: ItemEntity) = withContext(ioDispatcher) {
        itemsDao.updateItem(item)
    }

    override suspend fun updateItems(items: List<ItemEntity>) = withContext(ioDispatcher) {
        itemsDao.updateItems(items)
    }

    override suspend fun clearAllItems() = withContext(ioDispatcher) {
        itemsDao.deleteItems()
    }

    override suspend fun clearItem(itemId: String) = withContext<Unit>(ioDispatcher) {
        itemsDao.deleteItemById(itemId)
    }
}