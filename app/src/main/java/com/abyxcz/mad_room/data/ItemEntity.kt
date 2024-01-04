package com.abyxcz.mad_room.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var pk: Long = 0,
    @SerializedName("_id")
    val id: String,
    @SerializedName("id")
    val itemId: String?
)