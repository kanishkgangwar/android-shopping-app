package com.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_search_table")
data class RecentSearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val query: String
)