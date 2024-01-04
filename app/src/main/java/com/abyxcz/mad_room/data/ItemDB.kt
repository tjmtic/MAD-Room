package com.abyxcz.mad_room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [ItemEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ItemDB : RoomDatabase() {
    abstract fun ItemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemDB? = null
        fun getInstance(context: Context, password: String): ItemDB {
            val path = context.getDatabasePath("user").absolutePath
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDB::class.java,
                    path,
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}